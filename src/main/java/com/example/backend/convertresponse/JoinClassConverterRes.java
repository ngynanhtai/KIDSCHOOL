package com.example.backend.convertresponse;


import com.example.backend.converter.ClassConverter;
import com.example.backend.converter.SchoolConverter;
import com.example.backend.converter.StudentConverter;
import com.example.backend.dtoresponse.JoinClassDTORes;
import com.example.backend.model.JoinClassEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinClassConverterRes {

    @Autowired
    private StudentConverter studentConverter;

    @Autowired
    private ClassConverter classConverter;

    @Autowired
    private SchoolConverter schoolConverter;

    public JoinClassDTORes toDTO(JoinClassEntity joinClassEntity){
        JoinClassDTORes joinClassDTORes = new JoinClassDTORes();
        joinClassDTORes.setId(joinClassEntity.getId());
        joinClassDTORes.setJoinDate(joinClassEntity.getJoinDate());
        joinClassDTORes.setFee(joinClassEntity.getFee());
        joinClassDTORes.setNote(joinClassEntity.getNote());
        joinClassDTORes.setStatus(joinClassEntity.getStatus());
        joinClassDTORes.setStudentDTO(joinClassEntity.getStudentEntity() != null ? studentConverter.toDTO(joinClassEntity.getStudentEntity()) : null);
        joinClassDTORes.setClassDTO(joinClassEntity.getClassEntity() != null ? classConverter.toDTO(joinClassEntity.getClassEntity()) : null);
        joinClassDTORes.setSchoolDTO(joinClassEntity.getSchoolEntity() != null ? schoolConverter.toDTO(joinClassEntity.getSchoolEntity()) : null);
        return joinClassDTORes;
    }

}
