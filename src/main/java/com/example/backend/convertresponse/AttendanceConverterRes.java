package com.example.backend.convertresponse;


import com.example.backend.converter.ClassConverter;
import com.example.backend.converter.StudentConverter;
import com.example.backend.dtoresponse.AttendanceDTORes;
import com.example.backend.model.AttendanceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttendanceConverterRes {

    @Autowired
    private ClassConverter classConverter;

    @Autowired
    private StudentConverter studentConverter;

    public AttendanceDTORes toDTO(AttendanceEntity attendanceEntity){
        AttendanceDTORes attendanceDTORes = new AttendanceDTORes();
        attendanceDTORes.setId(attendanceEntity.getId());
        attendanceDTORes.setStudentName(attendanceEntity.getStudentName());
        attendanceDTORes.setAttendanceDate(attendanceEntity.getAttendanceDate());
        attendanceDTORes.setNote(attendanceEntity.getNote());

        attendanceDTORes.setClassDTO(attendanceEntity.getClassEntity() != null ? classConverter.toDTO(attendanceEntity.getClassEntity()) : null);
        attendanceDTORes.setStudentDTO(attendanceEntity.getStudentEntity() != null ? studentConverter.toDTO(attendanceEntity.getStudentEntity()) : null);
        return  attendanceDTORes;
    }
}
