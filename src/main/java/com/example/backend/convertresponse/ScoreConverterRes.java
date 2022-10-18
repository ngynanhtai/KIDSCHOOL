package com.example.backend.convertresponse;


import com.example.backend.converter.EmployeeConverter;
import com.example.backend.converter.StudentConverter;
import com.example.backend.converter.SubjectConverter;
import com.example.backend.dtoresponse.ScoreDTORes;
import com.example.backend.model.SchoolEntity;
import com.example.backend.model.ScoreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class ScoreConverterRes {

    @Autowired
    private SubjectConverter subjectConverter;

    @Autowired
    private StudentConverter studentConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    public ScoreDTORes toDTO(ScoreEntity scoreEntity) throws UnsupportedEncodingException {
        ScoreDTORes scoreDTORes = new ScoreDTORes();
        scoreDTORes.setId(scoreEntity.getId());
        scoreDTORes.setScore(scoreEntity.getScore());
        scoreDTORes.setNote(scoreEntity.getNote());
        scoreDTORes.setStatus(scoreEntity.getStatus());
        scoreDTORes.setSubjectDTO(scoreEntity.getSubjectEntity() != null ? subjectConverter.toDTO(scoreEntity.getSubjectEntity()) : null);
        scoreDTORes.setStudentDTO(scoreEntity.getStudentEntity() != null ? studentConverter.toDTO(scoreEntity.getStudentEntity()) : null);
        scoreDTORes.setEmployeeDTO(scoreEntity.getEmployeeEntity() != null ? employeeConverter.toDTO(scoreEntity.getEmployeeEntity()) : null);
        return scoreDTORes;
    }
}
