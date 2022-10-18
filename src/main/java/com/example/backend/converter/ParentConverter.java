package com.example.backend.converter;

import com.example.backend.dto.ParentDTO;
import com.example.backend.model.ParentEntity;
import org.springframework.stereotype.Component;

@Component
public class ParentConverter {
    public ParentDTO toDTO(ParentEntity parentEntity){
        ParentDTO parentDTO = new ParentDTO();
        parentDTO.setId(parentEntity.getId());
        parentDTO.setLastName(parentEntity.getLastName());
        parentDTO.setFirstName(parentEntity.getFirstName());
        parentDTO.setGender(parentEntity.getGender());
        parentDTO.setPhone(parentEntity.getPhone());
        parentDTO.setBirthday(parentEntity.getBirthday());
        parentDTO.setAddress(parentEntity.getAddress());
        parentDTO.setEmail(parentEntity.getEmail());
        return parentDTO;
    }

    public ParentEntity toEntity(ParentDTO parentDTO){
        ParentEntity parentEntity = new ParentEntity();
        parentEntity.setId(parentDTO.getId());
        parentEntity.setLastName(parentDTO.getLastName());
        parentEntity.setFirstName(parentDTO.getFirstName());
        parentEntity.setGender(parentDTO.getGender());
        parentEntity.setPhone(parentDTO.getPhone());
        parentEntity.setBirthday(parentDTO.getBirthday());
        parentEntity.setAddress(parentDTO.getAddress());
        parentEntity.setEmail(parentDTO.getEmail());
        return parentEntity;
    }

    public ParentEntity toEntity(ParentEntity result, ParentDTO parentDTO){
        result.setLastName(parentDTO.getLastName());
        result.setFirstName(parentDTO.getFirstName());
        result.setGender(parentDTO.getGender());
        result.setPhone(parentDTO.getPhone());
        result.setBirthday(parentDTO.getBirthday());
        result.setAddress(parentDTO.getAddress());
        result.setEmail(parentDTO.getEmail());
        return result;
    }
}
