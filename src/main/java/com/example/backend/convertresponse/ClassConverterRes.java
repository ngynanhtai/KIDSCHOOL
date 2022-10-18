package com.example.backend.convertresponse;


import com.example.backend.converter.*;
import com.example.backend.dto.*;
import com.example.backend.dtoresponse.ClassDTORes;
import com.example.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClassConverterRes {

    @Autowired
    private SchoolConverter schoolConverter;

    @Autowired
    private TypeClassConverter typeClassConverter;

    @Autowired
    private LevelClassConverter levelClassConverter;

    @Autowired
    private JoinClassConverter joinClassConverter;

    @Autowired
    private TimetableConverter timetableConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private AttendanceConverter attendanceConverter;

    @Autowired
    private SubjectConverter subjectConverter;

    @Autowired
    private StudentConverter studentConverter;

    public ClassDTORes toDTO(ClassEntity classEntity) throws UnsupportedEncodingException {
        ClassDTORes classDTORes = new ClassDTORes();
        classDTORes.setId(classEntity.getId());
        classDTORes.setClassId(classEntity.getClassId());
        classDTORes.setName(classEntity.getName());
        classDTORes.setFee(classEntity.getFee());
        classDTORes.setStartDate(classEntity.getStartDate());
        classDTORes.setEndDate(classEntity.getEndDate());
        classDTORes.setStatus(classEntity.getStatus());
        classDTORes.setCapacity(classEntity.getCapacity());
        classDTORes.setSchoolDTO(classEntity.getSchoolEntity() != null ? schoolConverter.toDTO(classEntity.getSchoolEntity()) : null);
        classDTORes.setTypeClassDTO(classEntity.getTypeClassEntity() != null ? typeClassConverter.toDTO(classEntity.getTypeClassEntity()) : null);
        classDTORes.setLevelClassDTO(classEntity.getLevelClassEntity() != null ? levelClassConverter.toDTO(classEntity.getLevelClassEntity()) : null);
        List<JoinClassDTO> joinClassDTOS = new ArrayList<>();
        if(classEntity.getJoinClassEntities() != null && classEntity.getJoinClassEntities().size() > 0){
            for (JoinClassEntity joinClassEntity : classEntity.getJoinClassEntities()){
                joinClassDTOS.add(joinClassConverter.toDTO(joinClassEntity));
            }
        }
        classDTORes.setJoinClassDTOS(joinClassDTOS);
        List<TimetableDTO> timetableDTOS = new ArrayList<>();
        if(classEntity.getTimeTableEntities() != null && classEntity.getTimeTableEntities().size() > 0){
            for (TimeTableEntity timeTableEntity : classEntity.getTimeTableEntities()){
                timetableDTOS.add(timetableConverter.toDTO(timeTableEntity));
            }
        }
        classDTORes.setTimetableDTOS(timetableDTOS);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        if(classEntity.getEmployeeEntities() != null && classEntity.getEmployeeEntities().size() > 0){
            for (EmployeeEntity employeeEntity : classEntity.getEmployeeEntities()){
                employeeDTOS.add(employeeConverter.toDTO(employeeEntity));
            }
        }
        classDTORes.setEmployeeDTOS(employeeDTOS);
        List<AttendanceDTO> attendanceDTOS = new ArrayList<>();
        if(classEntity.getAttendanceEntities() != null && classEntity.getAttendanceEntities().size() > 0){
            for (AttendanceEntity attendanceEntity : classEntity.getAttendanceEntities()){
                attendanceDTOS.add(attendanceConverter.toDTO(attendanceEntity));
            }
        }
        classDTORes.setAttendanceDTOS(attendanceDTOS);
        List<SubjectDTO> subjectDTOS = new ArrayList<>();
        if(classEntity.getSubjectEntities() != null && classEntity.getSubjectEntities().size() > 0){
            for (SubjectEntity subjectEntity : classEntity.getSubjectEntities()){
                subjectDTOS.add(subjectConverter.toDTO(subjectEntity));
            }
        }
        classDTORes.setSubjectDTOS(subjectDTOS);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        if(classEntity.getStudentEntities() != null && classEntity.getStudentEntities().size() > 0){
            for (StudentEntity studentEntity : classEntity.getStudentEntities()){
                studentDTOS.add(studentConverter.toDTO(studentEntity));
            }
        }
        classDTORes.setStudentDTOS(studentDTOS);
        return classDTORes;
    }
}
