package com.example.backend.converter;


import com.example.backend.dto.StudentDTO;
import com.example.backend.model.StudentEntity;
import com.example.backend.repository.ParentRepository;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class StudentConverter {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private ParentRepository parentRepository;

    public StudentDTO toDTO(StudentEntity studentEntity){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentEntity.getId());
        studentDTO.setAccountId(studentEntity.getAccountId());
        studentDTO.setPassword(studentEntity.getPassword());
        studentDTO.setFirstName(studentEntity.getFirstName());
        studentDTO.setLastName(studentEntity.getLastName());
        studentDTO.setGender(studentEntity.getGender());
        if(studentEntity.getImage() != null){
            String imageBase64 = "data:image/jpeg;base64, " +  Base64.getEncoder().encodeToString(studentEntity.getImage());
            studentDTO.setImage(imageBase64);
        }
        studentDTO.setAge(studentEntity.getAge());
        studentDTO.setBirthday(studentEntity.getBirthday());
        studentDTO.setPhone(studentEntity.getPhone());
        studentDTO.setEmail(studentEntity.getEmail());
        studentDTO.setAddress(studentEntity.getAddress());
        studentDTO.setStartDate(studentEntity.getStartDate());
        studentDTO.setEndDate(studentEntity.getEndDate());
        studentDTO.setCreateAt(studentEntity.getCreateAt());
        studentDTO.setStatus(studentEntity.getStatus());
        if (studentEntity.getRoleEntity() != null) {
            studentDTO.setRole_id(studentEntity.getRoleEntity().getId());
        }
        if (studentEntity.getSchoolEntity() != null) {
            studentDTO.setRole_id(studentEntity.getSchoolEntity().getId());
        }
        if (studentEntity.getParentEntity() != null) {
            studentDTO.setRole_id(studentEntity.getParentEntity().getId());
        }
        return studentDTO;
    }

    public StudentEntity toEntity(StudentDTO studentDTO){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(studentDTO.getId());
        studentEntity.setAccountId(studentDTO.getAccountId());
        studentEntity.setPassword(studentDTO.getPassword());
        studentEntity.setFirstName(studentDTO.getFirstName());
        studentEntity.setLastName(studentDTO.getLastName());
        studentEntity.setGender(studentDTO.getGender());
        if(studentDTO.getImage() != null){
            String imageBase64 = studentDTO.getImage().split(",")[1];
            studentEntity.setImage(Base64.getDecoder().decode(imageBase64));
        }
        studentEntity.setAge(studentDTO.getAge());
        studentEntity.setBirthday(studentDTO.getBirthday());
        studentEntity.setPhone(studentDTO.getPhone());
        studentEntity.setEmail(studentDTO.getEmail());
        studentEntity.setAddress(studentDTO.getAddress());
        studentEntity.setStartDate(studentDTO.getStartDate());
        studentEntity.setEndDate(studentDTO.getEndDate());
        studentEntity.setCreateAt(studentDTO.getCreateAt());
        studentEntity.setStatus(studentDTO.getStatus());
        if (studentDTO.getRole_id() != null) {
            studentEntity.setRoleEntity(roleRepository.findById(studentDTO.getRole_id()).get());
        }
        if (studentDTO.getSchool_id() != null) {
            studentEntity.setSchoolEntity(schoolRepository.findById(studentDTO.getSchool_id()).get());
        }
        if (studentDTO.getParent_id() != null) {
            studentEntity.setParentEntity(parentRepository.findById(studentDTO.getParent_id()).get());
        }
        return studentEntity;
    }

    public StudentEntity toEntity(StudentEntity result, StudentDTO studentDTO){
        result.setAccountId(studentDTO.getAccountId());
        result.setPassword(studentDTO.getPassword());
        result.setFirstName(studentDTO.getFirstName());
        result.setLastName(studentDTO.getLastName());
        result.setGender(studentDTO.getGender());
        if(studentDTO.getImage() != null){
            String imageBase64 = studentDTO.getImage().split(",")[1];
            result.setImage(Base64.getDecoder().decode(imageBase64));
        }
        result.setAge(studentDTO.getAge());
        result.setBirthday(studentDTO.getBirthday());
        result.setPhone(studentDTO.getPhone());
        result.setEmail(studentDTO.getEmail());
        result.setAddress(studentDTO.getAddress());
        result.setStartDate(studentDTO.getStartDate());
        result.setEndDate(studentDTO.getEndDate());
        result.setCreateAt(studentDTO.getCreateAt());
        result.setStatus(studentDTO.getStatus());
        if (studentDTO.getRole_id() != null) {
            result.setRoleEntity(roleRepository.findById(studentDTO.getRole_id()).get());
        }
        if (studentDTO.getSchool_id() != null) {
            result.setSchoolEntity(schoolRepository.findById(studentDTO.getSchool_id()).get());
        }
        if (studentDTO.getParent_id() != null) {
            result.setParentEntity(parentRepository.findById(studentDTO.getParent_id()).get());
        }
        return result;
    }
}
