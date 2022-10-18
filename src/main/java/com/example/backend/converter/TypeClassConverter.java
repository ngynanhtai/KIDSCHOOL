package com.example.backend.converter;

import com.example.backend.dto.TypeClassDTO;
import com.example.backend.model.TypeClassEntity;
import org.springframework.stereotype.Component;

@Component
public class TypeClassConverter {
    public TypeClassDTO toDTO(TypeClassEntity typeClassEntity){
        TypeClassDTO typeClassDTO = new TypeClassDTO();
        typeClassDTO.setId(typeClassEntity.getId());
        typeClassDTO.setName(typeClassEntity.getName());
        return typeClassDTO;
    }

    public TypeClassEntity toEntity(TypeClassDTO typeClassDTO){
        TypeClassEntity typeClass = new TypeClassEntity();
        typeClass.setId(typeClassDTO.getId());
        typeClass.setName(typeClassDTO.getName());
        return typeClass;
    }

    public TypeClassEntity toEntity(TypeClassEntity result, TypeClassDTO typeClassDTO){
        result.setName(typeClassDTO.getName());
        return result;
    }
}
