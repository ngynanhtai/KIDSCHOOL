package com.example.backend.convertresponse;


import com.example.backend.converter.StudentConverter;
import com.example.backend.dto.StudentDTO;
import com.example.backend.dtoresponse.ParentDTORes;
import com.example.backend.model.ParentEntity;
import com.example.backend.model.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParentConverterRes {

    @Autowired
    private StudentConverter studentConverter;

    public ParentDTORes toDTO(ParentEntity parentEntity){
        ParentDTORes parentDTORes = new ParentDTORes();
        parentDTORes.setId(parentEntity.getId());
        parentDTORes.setFirstName(parentEntity.getFirstName());
        parentDTORes.setLastName(parentEntity.getLastName());
        parentDTORes.setGender(parentEntity.getGender());
        parentDTORes.setPhone(parentEntity.getPhone());
        parentDTORes.setBirthday(parentEntity.getBirthday());
        parentDTORes.setAddress(parentEntity.getAddress());
        parentDTORes.setEmail(parentEntity.getEmail());

        List<StudentDTO> studentDTOS = new ArrayList<>();
        if(parentEntity.getStudentEntities() != null && parentEntity.getStudentEntities().size() > 0){
            for (StudentEntity studentEntity : parentEntity.getStudentEntities()){
                studentDTOS.add(studentConverter.toDTO(studentEntity));
            }
        }
        parentDTORes.setStudentDTOS(studentDTOS);
        return parentDTORes;
    }
}
