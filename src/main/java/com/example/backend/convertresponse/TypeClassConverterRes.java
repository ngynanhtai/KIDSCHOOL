package com.example.backend.convertresponse;

import com.example.backend.converter.ClassConverter;
import com.example.backend.dto.ClassDTO;
import com.example.backend.dtoresponse.TypeClassDTORes;
import com.example.backend.model.ClassEntity;
import com.example.backend.model.TypeClassEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TypeClassConverterRes {

    @Autowired
    private ClassConverter classConverter;


    public TypeClassDTORes toDTO(TypeClassEntity typeClassEntity){
        TypeClassDTORes typeClassDTORes = new TypeClassDTORes();
        typeClassDTORes.setId(typeClassEntity.getId());
        typeClassDTORes.setName(typeClassEntity.getName());
        List<ClassDTO> classDTOS = new ArrayList<>();
        if(typeClassEntity.getClassEntities() != null && typeClassEntity.getClassEntities().size() > 0){
            for (ClassEntity classEntity : typeClassEntity.getClassEntities()){
                classDTOS.add(classConverter.toDTO(classEntity));
            }
        }
        typeClassDTORes.setClassDTOS(classDTOS);
        return typeClassDTORes;
    }
}
