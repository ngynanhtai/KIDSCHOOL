package com.example.backend.convertresponse;


import com.example.backend.converter.ClassConverter;
import com.example.backend.dto.ClassDTO;
import com.example.backend.dtoresponse.LevelClassDTORes;
import com.example.backend.model.ClassEntity;
import com.example.backend.model.LevelClassEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LevelClassConverterRes {

    @Autowired
    private ClassConverter classConverter;

    public LevelClassDTORes toDTO(LevelClassEntity levelClassEntity){
        LevelClassDTORes levelClassDTORes = new LevelClassDTORes();
        levelClassDTORes.setId(levelClassEntity.getId());
        levelClassDTORes.setName(levelClassEntity.getName());
        List<ClassDTO> classDTOS = new ArrayList<>();
        if(levelClassEntity.getClassEntities() != null && levelClassEntity.getClassEntities().size() > 0){
            for (ClassEntity classEntity : levelClassEntity.getClassEntities()){
                classDTOS.add(classConverter.toDTO(classEntity));
            }
        }
        levelClassDTORes.setClassDTOS(classDTOS);
        return levelClassDTORes;
    }
}
