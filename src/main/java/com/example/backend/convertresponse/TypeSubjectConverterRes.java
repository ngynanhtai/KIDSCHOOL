package com.example.backend.convertresponse;

import com.example.backend.converter.SubjectConverter;
import com.example.backend.dto.SubjectDTO;
import com.example.backend.dtoresponse.TypeSubjectDTORes;
import com.example.backend.model.SubjectEntity;
import com.example.backend.model.TypeSubjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TypeSubjectConverterRes {

    @Autowired
    private SubjectConverter subjectConverter;

    public TypeSubjectDTORes toDTO(TypeSubjectEntity typeSubjectEntity){
        TypeSubjectDTORes typeSubjectDTORes = new TypeSubjectDTORes();
        typeSubjectDTORes.setId(typeSubjectEntity.getId());
        typeSubjectDTORes.setName(typeSubjectEntity.getName());
        List<SubjectDTO> subjectDTOS = new ArrayList<>();
        if(typeSubjectEntity.getSubjectEntities() != null && typeSubjectEntity.getSubjectEntities().size() > 0){
            for (SubjectEntity subjectEntity : typeSubjectEntity.getSubjectEntities()){
                subjectDTOS.add(subjectConverter.toDTO(subjectEntity));
            }
        }
        typeSubjectDTORes.setSubjectDTOS(subjectDTOS);
        return typeSubjectDTORes;
    }
}
