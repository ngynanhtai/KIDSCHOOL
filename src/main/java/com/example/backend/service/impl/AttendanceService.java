package com.example.backend.service.impl;


import com.example.backend.converter.AttendanceConverter;
import com.example.backend.convertresponse.AttendanceConverterRes;
import com.example.backend.dto.AttendanceDTO;
import com.example.backend.dto.etc.AttendanceSheetDTO;
import com.example.backend.dto.etc.ClassMemberDTO;
import com.example.backend.dtoresponse.AttendanceDTORes;
import com.example.backend.dtoresponse.StudentDTORes;
import com.example.backend.model.AttendanceEntity;
import com.example.backend.model.StudentEntity;
import com.example.backend.repository.AttendanceRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.IAttendanceService;
import com.example.backend.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService implements IAttendanceService {

    private AttendanceEntity entity;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private IClassService classService;

    @Autowired
    private AttendanceConverter attendanceConverter;

    @Autowired
    private AttendanceConverterRes attendanceConverterRes;

    @Override
    public AttendanceDTORes findOne(Long id){
        Optional<AttendanceEntity> data = attendanceRepository.findById(id);
        return data.isPresent() ? attendanceConverterRes.toDTO(data.get()) : null;

    }

    @Override
    public List<AttendanceDTORes> create(AttendanceSheetDTO attendanceSheetDTO) {
        String absentDate = attendanceSheetDTO.getDate();
        String absentDateConvertFromMillisecond = convertMillisecondToDate(absentDate);
        List<ClassMemberDTO> classMember = attendanceSheetDTO.getClassMember();
        List<AttendanceDTORes> attendanceDTOResList = new ArrayList<>();
        List<AttendanceEntity> studentAlreadyAttendance = attendanceRepository.findAll();
        for (AttendanceEntity item : studentAlreadyAttendance) {
            if (convertMillisecondToDate(item.getAttendanceDate()).equals(absentDateConvertFromMillisecond)) {
                attendanceRepository.delete(item);
            }
        }
        for (ClassMemberDTO item : classMember) {
            if (item.getIsAbsent()) {
                AttendanceDTO attendanceDTO = new AttendanceDTO();
                attendanceDTO.setAttendanceDate(absentDate);
                attendanceDTO.setNote(item.getNote());
                attendanceDTO.setStudentName(item.getName());
                attendanceDTO.setClass_id(attendanceSheetDTO.getClass_id());
                attendanceDTO.setStudent_id(item.getId());
                attendanceDTOResList.add(attendanceConverterRes.toDTO(attendanceRepository.save(attendanceConverter.toEntity(attendanceDTO))));
            }
        }
        return attendanceDTOResList;
    }

    @Override
    public void delete(Long id) {
        attendanceRepository.deleteById(id);
    }

    @Override
    public AttendanceDTORes update(AttendanceDTO attendanceDTO) {
        AttendanceEntity currEntity = attendanceRepository.findById(attendanceDTO.getId()).get();
        entity = attendanceConverter.toEntity(currEntity, attendanceDTO);
        return attendanceConverterRes.toDTO(attendanceRepository.save(entity));
    }

    @Override
    public List<AttendanceDTORes> findAll() {
        List<AttendanceDTORes> result = new ArrayList<>();
        List<AttendanceEntity> entityList = attendanceRepository.findAll();
        for (AttendanceEntity item : entityList) {
            result.add(attendanceConverterRes.toDTO(item));
        }
        return result;
    }

    @Override
    public AttendanceSheetDTO attendanceSheet(Long classId) throws UnsupportedEncodingException {
        AttendanceSheetDTO attendanceSheetDTO = new AttendanceSheetDTO();
        List<ClassMemberDTO> classMember = new ArrayList<>();
        List<StudentDTORes> studentList = classService.findAllStudentByClassID(classId);
        for (StudentDTORes item : studentList) {
            ClassMemberDTO dto = new ClassMemberDTO();
            dto.setId(item.getId());
            dto.setName(item.getFirstName() + " " + item.getLastName());
            classMember.add(dto);
        }
        attendanceSheetDTO.setClass_id(classId);
        attendanceSheetDTO.setClassMember(classMember);
        return attendanceSheetDTO;
    }

    private String convertMillisecondToDate(String millisecond) {
        LocalDate localDate = Instant.ofEpochMilli(Long.parseLong(millisecond)).atZone(ZoneId.of("UTC")).toLocalDate();
        DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("u-M-d");
        return localDate.format(formatter);
    }
}
