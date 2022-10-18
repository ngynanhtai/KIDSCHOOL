package com.example.backend.convertresponse;


import com.example.backend.converter.*;
import com.example.backend.dto.*;
import com.example.backend.dtoresponse.SchoolDTORes;
import com.example.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SchoolConverterRes {

    @Autowired
    private ClassConverter classConverter;

    @Autowired
    private StudentConverter studentConverter;

    @Autowired
    private SuggestionConverter suggestionConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private FeedbackConverter feedbackConverter;

    @Autowired
    private EventConverter eventConverter;

    @Autowired
    private JobApplicationConverter jobApplicationConverter;

    @Autowired
    private AnnouncementConverter announcementConverter;

    @Autowired
    private JoinClassConverter joinClassConverter;

    public SchoolDTORes toDTO(SchoolEntity schoolEntity) throws UnsupportedEncodingException {
        SchoolDTORes schoolDTORes = new SchoolDTORes();
        schoolDTORes.setId(schoolEntity.getId());
        schoolDTORes.setName(schoolEntity.getName());
        schoolDTORes.setAddress(schoolEntity.getAddress());
        schoolDTORes.setPhone(schoolEntity.getPhone());
        schoolDTORes.setStatus(schoolEntity.getStatus());

        List<ClassDTO> classDTOS = new ArrayList<>();
        if(schoolEntity.getClassEntities() != null && schoolEntity.getClassEntities().size() > 0){
            for (ClassEntity classEntity : schoolEntity.getClassEntities()){
                classDTOS.add(classConverter.toDTO(classEntity));
            }
        }
        schoolDTORes.setClassDTOS(classDTOS);

        List<StudentDTO> studentDTOS = new ArrayList<>();
        if(schoolEntity.getStudentEntities() != null && schoolEntity.getStudentEntities().size() > 0){
            for (StudentEntity studentEntity : schoolEntity.getStudentEntities()){
                studentDTOS.add(studentConverter.toDTO(studentEntity));
            }
        }
        schoolDTORes.setStudentDTOS(studentDTOS);
        List<SuggestionDTO> suggestionDTOS = new ArrayList<>();
        for (SuggestionEntity suggestionEntity : schoolEntity.getSuggestionEntities()){
            suggestionDTOS.add(suggestionConverter.toDTO(suggestionEntity));
        }
        schoolDTORes.setSuggestionDTOS(suggestionDTOS);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (EmployeeEntity employeeEntity : schoolEntity.getEmployeeEntities()){
            employeeDTOS.add(employeeConverter.toDTO(employeeEntity));
        }
        schoolDTORes.setEmployeeDTOS(employeeDTOS);

        List<FeedbackDTO> feedbackDTOS = new ArrayList<>();
        if(schoolEntity.getFeedbackEntities() != null && schoolEntity.getFeedbackEntities().size() > 0){
            for (FeedbackEntity feedbackEntity : schoolEntity.getFeedbackEntities()){
                feedbackDTOS.add(feedbackConverter.toDTO(feedbackEntity));
            }
        }
        schoolDTORes.setFeedbackDTOS(feedbackDTOS);

        List<EventDTO> eventDTOS = new ArrayList<>();
        if(schoolEntity.getEventEntities() != null && schoolEntity.getEventEntities().size() > 0){
            for (EventEntity eventEntity : schoolEntity.getEventEntities()){
                eventDTOS.add(eventConverter.toDTO(eventEntity));
            }
        }
        schoolDTORes.setEventDTOS(eventDTOS);

        List<JobApplicationDTO> jobApplicationDTOS = new ArrayList<>();
        if(schoolEntity.getJobApplicationEntities() != null && schoolEntity.getJobApplicationEntities().size() > 0){
            for (JobApplicationEntity jobApplicationEntity : schoolEntity.getJobApplicationEntities()){
                jobApplicationDTOS.add(jobApplicationConverter.toDTO(jobApplicationEntity));
            }
        }
        schoolDTORes.setJobApplicationDTOS(jobApplicationDTOS);

        List<AnnouncementDTO> announcementDTOS = new ArrayList<>();
        if(schoolEntity.getAnnouncementEntities() != null && schoolEntity.getAnnouncementEntities().size() > 0){
            for (AnnouncementEntity announcementEntity : schoolEntity.getAnnouncementEntities()){
                announcementDTOS.add(announcementConverter.toDTO(announcementEntity));
            }
        }
        schoolDTORes.setAnnouncementDTOS(announcementDTOS);

        List<JoinClassDTO> joinClassDTOS = new ArrayList<>();
        if(schoolEntity.getJoinClassEntities() != null && schoolEntity.getJoinClassEntities().size() > 0){
            for (JoinClassEntity joinClassEntity : schoolEntity.getJoinClassEntities()){
                joinClassDTOS.add(joinClassConverter.toDTO(joinClassEntity));
            }
        }
        schoolDTORes.setJoinClassDTOS(joinClassDTOS);
        return schoolDTORes;
    }
}
