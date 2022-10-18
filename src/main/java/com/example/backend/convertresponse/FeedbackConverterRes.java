package com.example.backend.convertresponse;


import com.example.backend.converter.EmployeeConverter;
import com.example.backend.converter.SchoolConverter;
import com.example.backend.converter.StudentConverter;
import com.example.backend.converter.SubjectConverter;
import com.example.backend.dtoresponse.FeedbackDTORes;
import com.example.backend.model.FeedbackEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class FeedbackConverterRes {

    @Autowired
    private StudentConverter studentConverter;

    @Autowired
    private SubjectConverter subjectConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private SchoolConverter schoolConverter;

    public FeedbackDTORes toDTO(FeedbackEntity feedbackEntity) throws UnsupportedEncodingException {
        FeedbackDTORes feedbackDTORes = new FeedbackDTORes();
        feedbackDTORes.setId(feedbackEntity.getId());
        feedbackDTORes.setTitle(feedbackEntity.getTitle());
        feedbackDTORes.setContent(feedbackEntity.getContent());
        feedbackDTORes.setIsReply(feedbackEntity.getIsReply());
        feedbackDTORes.setReplyContent(feedbackEntity.getReplyContent());
        feedbackDTORes.setCreateAt(feedbackEntity.getCreateAt());
        feedbackDTORes.setStudentDTO(feedbackEntity.getStudentEntity() != null ? studentConverter.toDTO(feedbackEntity.getStudentEntity()) : null);
        feedbackDTORes.setSubjectDTO(feedbackEntity.getSubjectEntity() != null ? subjectConverter.toDTO(feedbackEntity.getSubjectEntity()) : null);
        feedbackDTORes.setEmployeeDTO(feedbackEntity.getEmployeeEntity() != null ? employeeConverter.toDTO(feedbackEntity.getEmployeeEntity()) : null);
        feedbackDTORes.setSchoolDTO(feedbackEntity.getSchoolEntity() != null ? schoolConverter.toDTO(feedbackEntity.getSchoolEntity()) : null);
        return feedbackDTORes;
    }
}
