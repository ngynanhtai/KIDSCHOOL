package com.example.backend.converter;


import com.example.backend.dto.SymptomDTO;
import com.example.backend.model.SymptomEntity;
import org.springframework.stereotype.Component;

@Component
public class SymptomConverter {
    public SymptomDTO toDTO(SymptomEntity symptomEntity){
        SymptomDTO symptomDTO = new SymptomDTO();
        symptomDTO.setId(symptomEntity.getId());
        symptomDTO.setName(symptomEntity.getName());
        return symptomDTO;
    }

    public SymptomEntity toEntity(SymptomDTO symptomDTO){
        SymptomEntity symptomEntity = new SymptomEntity();
        symptomEntity.setId(symptomDTO.getId());
        symptomEntity.setName(symptomDTO.getName());
        return symptomEntity;
    }

    public SymptomEntity toEntity(SymptomEntity result, SymptomDTO symptomDTO){
        result.setName(symptomDTO.getName());
        return result;
    }
}
