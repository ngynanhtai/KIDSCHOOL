package com.example.backend.convertresponse;

import com.example.backend.converter.HealthReportConverter;
import com.example.backend.dto.HealthReportDTO;
import com.example.backend.dtoresponse.SymptomDTORes;
import com.example.backend.model.HealthReportEntity;
import com.example.backend.model.SymptomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SymptomConverterRes {

    @Autowired
    private HealthReportConverter healthReportConverter;

    public SymptomDTORes toDTO(SymptomEntity symptomEntity){
        SymptomDTORes symptomDTORes = new SymptomDTORes();
        symptomDTORes.setId(symptomEntity.getId());
        symptomDTORes.setName(symptomEntity.getName());
        List<HealthReportDTO> healthReportDTOS = new ArrayList<>();
        if(symptomEntity.getHealthReportEntities() != null && symptomEntity.getHealthReportEntities().size() > 0){
            for (HealthReportEntity healthReportEntity : symptomEntity.getHealthReportEntities()){
                healthReportDTOS.add(healthReportConverter.toDTO(healthReportEntity));
            }
        }
        symptomDTORes.setHealthReportDTOS(healthReportDTOS);
        return symptomDTORes;
    }
}
