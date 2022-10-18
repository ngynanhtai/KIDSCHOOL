package com.example.backend.convertresponse;

import com.example.backend.converter.*;
import com.example.backend.dto.*;
import com.example.backend.dtoresponse.StudentDTORes;
import com.example.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class StudentConverterRes {

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private SchoolConverter schoolConverter;

    @Autowired
    private ParentConverter parentConverter;

    @Autowired
    private FeedbackConverter feedbackConverter;

    @Autowired
    private HealthReportConverter healthReportConverter;

    @Autowired
    private AttendanceConverter attendanceConverter;

    @Autowired
    private ScoreConverter scoreConverter;

    @Autowired
    private ClassConverter classConverter;

    @Autowired
    private JoinClassConverter joinClassConverter;

    public StudentDTORes toDTO(StudentEntity studentEntity) throws UnsupportedEncodingException {
        StudentDTORes studentDTORes = new StudentDTORes();
        studentDTORes.setId(studentEntity.getId());
        studentDTORes.setAccountId(studentEntity.getAccountId());
        studentDTORes.setPassword(studentEntity.getPassword());
        studentDTORes.setFirstName(studentEntity.getFirstName());
        studentDTORes.setLastName(studentEntity.getLastName());
        studentDTORes.setGender(studentEntity.getGender());
        if(studentEntity.getImage() != null){
            String imageBase64 = "data:image/jpeg;base64," +  Base64.getEncoder().encodeToString(studentEntity.getImage());
            studentDTORes.setImage(imageBase64);
        }
        studentDTORes.setAge(studentEntity.getAge());
        studentDTORes.setBirthday(studentEntity.getBirthday());
        studentDTORes.setPhone(studentEntity.getPhone());
        studentDTORes.setEmail(studentEntity.getEmail());
        studentDTORes.setAddress(studentEntity.getAddress());
        studentDTORes.setStartDate(studentEntity.getStartDate());
        studentDTORes.setEndDate(studentEntity.getEndDate());
        studentDTORes.setCreateAt(studentEntity.getCreateAt());
        studentDTORes.setStatus(studentEntity.getStatus());
        studentDTORes.setRoleDTO(studentEntity.getRoleEntity() != null ? roleConverter.toDTO(studentEntity.getRoleEntity()) : null);
        studentDTORes.setSchoolDTO(studentEntity.getSchoolEntity() != null ? schoolConverter.toDTO(studentEntity.getSchoolEntity()) : null);
        studentDTORes.setParentDTO(studentEntity.getParentEntity() != null ? parentConverter.toDTO(studentEntity.getParentEntity()) : null);

        List<FeedbackDTO> feedbackDTOS = new ArrayList<>();
        if(studentEntity.getFeedbackEntities() != null && studentEntity.getFeedbackEntities().size() > 0){
            for (FeedbackEntity feedbackEntity : studentEntity.getFeedbackEntities()){
                feedbackDTOS.add(feedbackConverter.toDTO(feedbackEntity));
            }
        }
        studentDTORes.setFeedbackDTOS(feedbackDTOS);

        List<HealthReportDTO> healthReportDTOS = new ArrayList<>();
        if(studentEntity.getHealthReportEntities() != null && studentEntity.getHealthReportEntities().size() > 0){
            for (HealthReportEntity healthReportEntity : studentEntity.getHealthReportEntities()){
                healthReportDTOS.add(healthReportConverter.toDTO(healthReportEntity));
            }
        }
        studentDTORes.setHealthReportDTOS(healthReportDTOS);

        List<AttendanceDTO> attendanceDTOS = new ArrayList<>();
        if(studentEntity.getAttendanceEntities() != null && studentEntity.getAttendanceEntities().size() > 0){
            for (AttendanceEntity attendanceEntity : studentEntity.getAttendanceEntities()){
                attendanceDTOS.add(attendanceConverter.toDTO(attendanceEntity));
            }
        }
        studentDTORes.setAttendanceDTOS(attendanceDTOS);

        List<ScoreDTO> scoreDTOS = new ArrayList<>();
        if(studentEntity.getScoreEntities() != null && studentEntity.getScoreEntities().size() > 0){
            for (ScoreEntity scoreEntity : studentEntity.getScoreEntities()){
                scoreDTOS.add(scoreConverter.toDTO(scoreEntity));
            }
        }
        studentDTORes.setScoreDTOS(scoreDTOS);

        List<ClassDTO> classDTOS = new ArrayList<>();
        if(studentEntity.getClassEntities() != null && studentEntity.getClassEntities().size() > 0){
            for (ClassEntity classEntity : studentEntity.getClassEntities()){
                classDTOS.add(classConverter.toDTO(classEntity));
            }
        }
        studentDTORes.setClassDTOS(classDTOS);

        List<JoinClassDTO> joinClassDTOS = new ArrayList<>();
        if(studentEntity.getJoinClassEntities() != null && studentEntity.getJoinClassEntities().size() > 0){
            for (JoinClassEntity joinClassEntity : studentEntity.getJoinClassEntities()){
                joinClassDTOS.add(joinClassConverter.toDTO(joinClassEntity));
            }
        }
        studentDTORes.setJoinClassDTOS(joinClassDTOS);
        return studentDTORes;
    }
}
