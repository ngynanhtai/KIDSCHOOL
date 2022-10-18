package com.example.backend.converter;

import com.example.backend.dto.SchoolDTO;
import com.example.backend.model.SchoolEntity;
import org.springframework.stereotype.Component;

@Component
public class SchoolConverter {
    public SchoolDTO toDTO(SchoolEntity schoolEntity){
        SchoolDTO schoolDTO = new SchoolDTO();
        schoolDTO.setId(schoolEntity.getId());
        schoolDTO.setName(schoolEntity.getName());
        schoolDTO.setAddress(schoolEntity.getAddress());
        schoolDTO.setStatus(schoolEntity.getStatus());
        schoolDTO.setPhone(schoolEntity.getPhone());
        return schoolDTO;
    }

    public SchoolEntity toEntity(SchoolDTO schoolDTO){
        SchoolEntity schoolEntity = new SchoolEntity();
        schoolEntity.setId(schoolDTO.getId());
        schoolEntity.setName(schoolDTO.getName());
        schoolEntity.setAddress(schoolDTO.getAddress());
        schoolEntity.setStatus(schoolDTO.getStatus());
        schoolEntity.setPhone(schoolDTO.getPhone());
        return schoolEntity;
    }

    public SchoolEntity toEntity(SchoolEntity result, SchoolDTO schoolDTO){
        result.setName(schoolDTO.getName());
        result.setAddress(schoolDTO.getAddress());
        result.setStatus(schoolDTO.getStatus());
        result.setPhone(schoolDTO.getPhone());
        return result;
    }
}
