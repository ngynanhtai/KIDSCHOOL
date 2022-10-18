package com.example.backend.convertresponse;

import com.example.backend.converter.StudentConverter;
import com.example.backend.converter.SymptomConverter;
import com.example.backend.dtoresponse.HealthReportDTORes;
import com.example.backend.model.HealthReportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HealthReportConverterRes {

    @Autowired
    private SymptomConverter symptomConverter;

    @Autowired
    private StudentConverter studentConverter;

    public HealthReportDTORes toDTO(HealthReportEntity healthReportEntity){
        HealthReportDTORes healthReportDTORes = new HealthReportDTORes();
        healthReportDTORes.setId(healthReportEntity.getId());
        healthReportDTORes.setNote(healthReportEntity.getNote());
        healthReportDTORes.setStatus(healthReportEntity.getStatus());
        healthReportDTORes.setCreateAt(healthReportEntity.getCreateAt());
        healthReportDTORes.setSymptomDTO(healthReportEntity.getSymptomEntity() != null ? symptomConverter.toDTO(healthReportEntity.getSymptomEntity()) : null);
        healthReportDTORes.setStudentDTO(healthReportEntity.getStudentEntity() != null ? studentConverter.toDTO(healthReportEntity.getStudentEntity()) : null);
        return healthReportDTORes;
    }
}
