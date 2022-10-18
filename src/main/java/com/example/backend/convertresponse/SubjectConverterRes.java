package com.example.backend.convertresponse;


import com.example.backend.converter.*;
import com.example.backend.dto.ClassDTO;
import com.example.backend.dto.FeedbackDTO;
import com.example.backend.dto.ScoreDTO;
import com.example.backend.dto.TimetableDTO;
import com.example.backend.dtoresponse.SubjectDTORes;
import com.example.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class SubjectConverterRes {

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private TypeSubjectConverter typeSubjectConverter;

    @Autowired
    private ClassConverter classConverter;

    @Autowired
    private FeedbackConverter feedbackConverter;

    @Autowired
    private TimetableConverter timetableConverter;

    @Autowired
    private ScoreConverter scoreConverter;

    public SubjectDTORes toDTO(SubjectEntity subjectEntity) throws UnsupportedEncodingException {
        SubjectDTORes subjectDTORes = new SubjectDTORes();
        subjectDTORes.setId(subjectEntity.getId());
        subjectDTORes.setName(subjectEntity.getName());
        subjectDTORes.setFee(subjectEntity.getFee());
        subjectDTORes.setHour(subjectEntity.getHour());
        subjectDTORes.setStartDate(subjectEntity.getStartDate());
        subjectDTORes.setEndDate(subjectEntity.getEndDate());
        subjectDTORes.setStatus(subjectEntity.getStatus());
        if (subjectEntity.getImage() != null){
            String imageBase64 = "data:image/jpeg;base64," +  Base64.getEncoder().encodeToString(subjectEntity.getImage());
            subjectDTORes.setImage(imageBase64);
        }
        subjectDTORes.setEmployeeDTO(subjectEntity.getEmployeeEntity() != null ? employeeConverter.toDTO(subjectEntity.getEmployeeEntity()) : null);
        subjectDTORes.setTypeSubjectDTO(subjectEntity.getTypeSubjectEntity() != null ? typeSubjectConverter.toDTO(subjectEntity.getTypeSubjectEntity()) : null);

        List<ClassDTO> classDTOS = new ArrayList<>();
        if(subjectEntity.getClassEntities() != null && subjectEntity.getClassEntities().size() > 0){
            for (ClassEntity classEntity : subjectEntity.getClassEntities()){
                classDTOS.add(classConverter.toDTO(classEntity));
            }
        }
        subjectDTORes.setClassDTOS(classDTOS);

        List<FeedbackDTO> feedbackDTOS = new ArrayList<>();
        if(subjectEntity.getFeedbackEntities() != null && subjectEntity.getFeedbackEntities().size() > 0){
            for (FeedbackEntity feedbackEntity : subjectEntity.getFeedbackEntities()){
                feedbackDTOS.add(feedbackConverter.toDTO(feedbackEntity));
            }
        }
        subjectDTORes.setFeedbackDTOS(feedbackDTOS);

        List<TimetableDTO> timetableDTOS = new ArrayList<>();
        if(subjectEntity.getTimeTableEntities() != null && subjectEntity.getTimeTableEntities().size() > 0){
            for (TimeTableEntity timeTableEntity : subjectEntity.getTimeTableEntities()){
                timetableDTOS.add(timetableConverter.toDTO(timeTableEntity));
            }
        }
        subjectDTORes.setTimetableDTOS(timetableDTOS);

        List<ScoreDTO> scoreDTOS = new ArrayList<>();
        if(subjectEntity.getScoreEntities() != null && subjectEntity.getScoreEntities().size() > 0){
            for (ScoreEntity scoreEntity : subjectEntity.getScoreEntities()){
                scoreDTOS.add(scoreConverter.toDTO(scoreEntity));
            }
        }
        subjectDTORes.setScoreDTOS(scoreDTOS);
        return subjectDTORes;
    }
}
