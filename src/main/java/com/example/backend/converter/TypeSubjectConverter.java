package com.example.backend.converter;

import com.example.backend.dto.TypeSubjectDTO;
import com.example.backend.model.TypeSubjectEntity;
import org.springframework.stereotype.Component;

@Component
public class TypeSubjectConverter {

    public TypeSubjectDTO toDTO(TypeSubjectEntity typeSubjectEntity){
        TypeSubjectDTO typeSubjectDTO = new TypeSubjectDTO();
        typeSubjectDTO.setId(typeSubjectEntity.getId());
        typeSubjectDTO.setName(typeSubjectEntity.getName());
        return typeSubjectDTO;
    }

    public TypeSubjectEntity toEntity(TypeSubjectDTO typeSubjectDTO){
        TypeSubjectEntity typeSubjectEntity = new TypeSubjectEntity();
        typeSubjectEntity.setId(typeSubjectDTO.getId());
        typeSubjectEntity.setName(typeSubjectDTO.getName());
        return typeSubjectEntity;
    }

    public TypeSubjectEntity toEntity(TypeSubjectEntity result, TypeSubjectDTO typeSubjectDTO){
        result.setName(typeSubjectDTO.getName());
        return result;
    }
}
