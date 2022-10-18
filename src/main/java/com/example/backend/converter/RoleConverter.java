package com.example.backend.converter;

import com.example.backend.dto.RoleDTO;
import com.example.backend.model.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    public RoleDTO toDTO(RoleEntity roleEntity){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(roleEntity.getId());
        roleDTO.setName(roleEntity.getName());
        return roleDTO;
    }

    public RoleEntity toEntity(RoleDTO roleDTO){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(roleDTO.getId());
        roleEntity.setName(roleDTO.getName());
        return roleEntity;
    }

    public RoleEntity toEntity(RoleEntity result, RoleDTO roleDTO){
        result.setName(roleDTO.getName());
        return result;
    }
}
