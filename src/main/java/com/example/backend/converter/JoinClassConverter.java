package com.example.backend.converter;

import com.example.backend.dto.JoinClassDTO;
import com.example.backend.model.JoinClassEntity;
import com.example.backend.repository.ClassRepository;
import com.example.backend.repository.SchoolRepository;
import com.example.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinClassConverter {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    public JoinClassDTO toDTO(JoinClassEntity entity) {
        JoinClassDTO result = new JoinClassDTO();
        result.setId(entity.getId());
        result.setNote(entity.getNote());
        result.setStatus(entity.getStatus());
        result.setFee(entity.getFee());
        result.setJoinDate(entity.getJoinDate());
        if (entity.getStudentEntity() != null) {
            result.setStudent_id(entity.getStudentEntity().getId());
        }
        if (entity.getClassEntity() != null) {
            result.setClass_id(entity.getClassEntity().getId());
        }
        if (entity.getSchoolEntity() != null) {
            result.setSchool_id(entity.getSchoolEntity().getId());
        }
        return result;
    }

    public JoinClassEntity toEntity(JoinClassDTO dto) {
        JoinClassEntity result = new JoinClassEntity();
        result.setId(dto.getId());
        result.setNote(dto.getNote());
        result.setStatus(dto.getStatus());
        result.setFee(dto.getFee());
        result.setJoinDate(dto.getJoinDate());
        if (dto.getStudent_id() != null) {
            result.setStudentEntity(studentRepository.findById(dto.getStudent_id()).get());
        }
        if (dto.getClass_id() != null) {
            result.setClassEntity(classRepository.findById(dto.getClass_id()).get());
        }
        if (dto.getSchool_id() != null) {
            result.setSchoolEntity(schoolRepository.findById(dto.getSchool_id()).get());
        }
        return result;
    }

    public JoinClassEntity toEntity(JoinClassEntity result, JoinClassDTO dto) {
        result.setNote(dto.getNote());
        result.setStatus(dto.getStatus());
        result.setFee(dto.getFee());
        result.setJoinDate(dto.getJoinDate());
        if (dto.getStudent_id() != null) {
            result.setStudentEntity(studentRepository.findById(dto.getStudent_id()).get());
        }
        if (dto.getClass_id() != null) {
            result.setClassEntity(classRepository.findById(dto.getClass_id()).get());
        }
        if (dto.getSchool_id() != null) {
            result.setSchoolEntity(schoolRepository.findById(dto.getSchool_id()).get());
        }
        return result;
    }
}
