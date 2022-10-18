package com.example.backend.converter;

import com.example.backend.dto.AttendanceDTO;
import com.example.backend.model.AttendanceEntity;
import com.example.backend.repository.ClassRepository;
import com.example.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttendanceConverter {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    public AttendanceDTO toDTO(AttendanceEntity entity) {
        AttendanceDTO result = new AttendanceDTO();
        result.setId(entity.getId());
        result.setNote(entity.getNote());
        result.setAttendanceDate(entity.getAttendanceDate());
        result.setStudentName(entity.getStudentName());
        if (entity.getStudentEntity() != null) {
            result.setStudent_id(entity.getStudentEntity().getId());
        }
        if (entity.getClassEntity() != null) {
            result.setClass_id(entity.getClassEntity().getId());
        }
        return result;
    }

    public AttendanceEntity toEntity(AttendanceDTO dto) {
        AttendanceEntity result = new AttendanceEntity();
        result.setId(dto.getId());
        result.setNote(dto.getNote());
        result.setAttendanceDate(dto.getAttendanceDate());
        result.setStudentName(dto.getStudentName());
        if (dto.getStudent_id() != null) {
            result.setStudentEntity(studentRepository.findById(dto.getStudent_id()).get());
        }
        if (dto.getClass_id() != null) {
            result.setClassEntity(classRepository.findById(dto.getClass_id()).get());
        }
        return result;
    }

    public AttendanceEntity toEntity(AttendanceEntity result, AttendanceDTO dto) {
        result.setNote(dto.getNote());
        result.setAttendanceDate(dto.getAttendanceDate());
        result.setStudentName(dto.getStudentName());
        if (dto.getStudent_id() != null) {
            result.setStudentEntity(studentRepository.findById(dto.getStudent_id()).get());
        }
        if (dto.getClass_id() != null) {
            result.setClassEntity(classRepository.findById(dto.getClass_id()).get());
        }
        return result;
    }
}
