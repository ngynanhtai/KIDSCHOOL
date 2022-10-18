package com.example.backend.convertresponse;


import com.example.backend.converter.*;
import com.example.backend.dto.*;
import com.example.backend.dtoresponse.EmployeeDTORes;
import com.example.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class EmployeeConverterRes {

    @Autowired
    private SchoolConverter schoolConverter;

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private LevelEmployeeConverter levelEmployeeConverter;

    @Autowired
    private SalaryConverter salaryConverter;

    @Autowired
    private SubjectConverter subjectConverter;

    @Autowired
    private SuggestionConverter suggestionConverter;

    @Autowired
    private FeedbackConverter feedbackConverter;

    @Autowired
    private ClassConverter classConverter;

    @Autowired
    private EventParticipateConverter eventParticipateConverter;

    @Autowired
    private ScoreConverter scoreConverter;

    @Autowired
    private AnnouncementConverter announcementConverter;

    public EmployeeDTORes toDTO(EmployeeEntity employeeEntity) throws UnsupportedEncodingException {
        EmployeeDTORes employeeDTORes = new EmployeeDTORes();
        employeeDTORes.setId(employeeEntity.getId());
        employeeDTORes.setAccountID(employeeEntity.getAccountID());
        employeeDTORes.setPassword(employeeEntity.getPassword());
        employeeDTORes.setFirstName(employeeEntity.getFirstName());
        employeeDTORes.setLastName(employeeEntity.getLastName());
        employeeDTORes.setBirthday(employeeEntity.getBirthday());
        employeeDTORes.setGender(employeeEntity.getGender());
        if(employeeEntity.getImage() != null){
            String imageBase64 = "data:image/jpeg;base64," +  Base64.getEncoder().encodeToString(employeeEntity.getImage());
            employeeDTORes.setImage(imageBase64);
        }
        employeeDTORes.setAge(employeeEntity.getAge());
        employeeDTORes.setPhone(employeeEntity.getPhone());
        employeeDTORes.setEmail(employeeEntity.getEmail());
        employeeDTORes.setAddress(employeeEntity.getAddress());
        employeeDTORes.setDegree(employeeEntity.getDegree());
        employeeDTORes.setStartDate(employeeEntity.getStartDate());
        employeeDTORes.setEndDate(employeeEntity.getEndDate());
        employeeDTORes.setCreateAt(employeeEntity.getCreateAt());
        employeeDTORes.setStatus(employeeEntity.getStatus());
        employeeDTORes.setSchoolDTO(employeeEntity.getSchoolEntity() != null ? schoolConverter.toDTO(employeeEntity.getSchoolEntity()) : null);
        employeeDTORes.setRoleDTO(employeeEntity.getRoleEntity() != null ? roleConverter.toDTO(employeeEntity.getRoleEntity()) : null);
        employeeDTORes.setLevelEmployeeDTO(employeeEntity.getLevelEmployeeEntity() != null ? levelEmployeeConverter.toDTO(employeeEntity.getLevelEmployeeEntity()) : null);
        List<SalaryDTO> salaryDTOS = new ArrayList<>();
        if(employeeEntity.getSalaryEntities() != null && employeeEntity.getSalaryEntities().size() > 0){
            for (SalaryEntity salaryEntity : employeeEntity.getSalaryEntities()){
                salaryDTOS.add(salaryConverter.toDTO(salaryEntity));
            }
        }
        employeeDTORes.setSalaryDTOS(salaryDTOS);
        List<SubjectDTO> subjectDTOS = new ArrayList<>();
        if(employeeEntity.getSubjectEntities() != null && employeeEntity.getSubjectEntities().size() > 0){
            for (SubjectEntity subjectEntity : employeeEntity.getSubjectEntities()){
                subjectDTOS.add(subjectConverter.toDTO(subjectEntity));
            }
        }
        employeeDTORes.setSubjectDTOS(subjectDTOS);
        List<SuggestionDTO> suggestionDTOS = new ArrayList<>();
        if(employeeEntity.getSuggestionEntities() != null && employeeEntity.getSuggestionEntities().size() > 0){
            for (SuggestionEntity suggestionEntity : employeeEntity.getSuggestionEntities()){
                suggestionDTOS.add(suggestionConverter.toDTO(suggestionEntity));
            }
        }
        employeeDTORes.setSuggestionDTOS(suggestionDTOS);
        List<FeedbackDTO> feedbackDTOS = new ArrayList<>();
        if(employeeEntity.getFeedbackEntities() != null && employeeEntity.getFeedbackEntities().size() > 0){
            for (FeedbackEntity feedbackEntity : employeeEntity.getFeedbackEntities()){
                feedbackDTOS.add(feedbackConverter.toDTO(feedbackEntity));
            }
        }
        employeeDTORes.setFeedbackDTOS(feedbackDTOS);
        List<ClassDTO> classDTOS = new ArrayList<>();
        if(employeeEntity.getClassEntities() != null && employeeEntity.getClassEntities().size() > 0){
            for (ClassEntity classEntity : employeeEntity.getClassEntities()){
                classDTOS.add(classConverter.toDTO(classEntity));
            }
        }
        employeeDTORes.setClassDTOS(classDTOS);
        List<EventParticipateDTO> eventParticipateDTOS = new ArrayList<>();
        if(employeeEntity.getEventParticipateEntities() != null && employeeEntity.getEventParticipateEntities().size() > 0){
            for (EventParticipateEntity eventParticipateEntity : employeeEntity.getEventParticipateEntities()){
                eventParticipateDTOS.add(eventParticipateConverter.toDTO(eventParticipateEntity));
            }
        }
        employeeDTORes.setEventParticipateDTOS(eventParticipateDTOS);
        List<ScoreDTO> scoreDTOS = new ArrayList<>();
        if(employeeEntity.getScoreEntities() != null && employeeEntity.getScoreEntities().size() > 0){
            for (ScoreEntity scoreEntity : employeeEntity.getScoreEntities()){
                scoreDTOS.add(scoreConverter.toDTO(scoreEntity));
            }
        }
        employeeDTORes.setScoreDTOS(scoreDTOS);
        List<AnnouncementDTO> announcementDTOS = new ArrayList<>();
        if(employeeEntity.getAnnouncementEntities() != null && employeeEntity.getAnnouncementEntities().size() > 0){
            for (AnnouncementEntity announcementEntity : employeeEntity.getAnnouncementEntities()){
                announcementDTOS.add(announcementConverter.toDTO(announcementEntity));
            }
        }
        employeeDTORes.setAnnouncementDTOS(announcementDTOS);
        return employeeDTORes;
    }
}
