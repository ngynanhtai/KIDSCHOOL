package com.example.backend.converter;

import com.example.backend.dto.EmployeeDTO;
import com.example.backend.model.EmployeeEntity;
import com.example.backend.repository.LevelEmployeeRepository;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Component
public class EmployeeConverter {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private LevelEmployeeRepository levelEmployeeRepository;

    public EmployeeDTO toDTO(EmployeeEntity entity) throws UnsupportedEncodingException {
        EmployeeDTO result = new EmployeeDTO();
        result.setId(entity.getId());
        result.setAccountID(entity.getAccountID());
        result.setPassword(entity.getPassword());
        result.setFirstName(entity.getFirstName());
        result.setLastName(entity.getLastName());
        result.setBirthday(entity.getBirthday());
        result.setGender(entity.getGender());
        if(entity.getImage() != null){
            String imageBase64 = "data:image/jpeg;base64, " +  Base64.getEncoder().encodeToString(entity.getImage());
            result.setImage(imageBase64);
        }
        result.setAge(entity.getAge());
        result.setPhone(entity.getPhone());
        result.setEmail(entity.getEmail());
        result.setAddress(entity.getAddress());
        result.setDegree(entity.getDegree());
        result.setStartDate(entity.getStartDate());
        result.setEndDate(entity.getEndDate());
        result.setCreateAt(entity.getCreateAt());
        result.setStatus(entity.getStatus());
        result.setLevelEmployee_id(entity.getLevelEmployeeEntity().getId());
        if(entity.getSchoolEntity() != null) {
            result.setSchool_id(entity.getSchoolEntity().getId());
        }
        if(entity.getRoleEntity() != null) {
            result.setSchool_id(entity.getRoleEntity().getId());
        }
        if(entity.getLevelEmployeeEntity() != null) {
            result.setSchool_id(entity.getLevelEmployeeEntity().getId());
        }
        return result;
    }

    public EmployeeEntity toEntity(EmployeeDTO dto) {
        EmployeeEntity result = new EmployeeEntity();
        result.setId(dto.getId());
        result.setAccountID(dto.getAccountID());
        result.setPassword(dto.getPassword());
        result.setFirstName(dto.getFirstName());
        result.setLastName(dto.getLastName());
        result.setBirthday(dto.getBirthday());
        result.setGender(dto.getGender());
        if(dto.getImage() != null){
            String imageBase64 = dto.getImage().split(",")[1];
            result.setImage(Base64.getDecoder().decode(imageBase64));
        }
        result.setAge(dto.getAge());
        result.setPhone(dto.getPhone());
        result.setEmail(dto.getEmail());
        result.setAddress(dto.getAddress());
        result.setDegree(dto.getDegree());
        result.setStartDate(dto.getStartDate());
        result.setEndDate(dto.getEndDate());
        result.setCreateAt(dto.getCreateAt());
        result.setStatus(dto.getStatus());
        if (dto.getSchool_id() != null) {
            result.setSchoolEntity(schoolRepository.findById(dto.getSchool_id()).get());
        }
        if (dto.getRole_id() != null) {
            result.setRoleEntity(roleRepository.findById(dto.getRole_id()).get());
        }
        if (dto.getLevelEmployee_id() != null) {
            result.setLevelEmployeeEntity(levelEmployeeRepository.findById(dto.getLevelEmployee_id()).get());
        }
        return result;
    }

    public EmployeeEntity toEntity(EmployeeEntity result, EmployeeDTO dto) {
        result.setAccountID(dto.getAccountID());
        result.setPassword(dto.getPassword());
        result.setFirstName(dto.getFirstName());
        result.setLastName(dto.getLastName());
        result.setBirthday(dto.getBirthday());
        result.setGender(dto.getGender());
        if(dto.getImage() != null){
            String imageBase64 = dto.getImage().split(",")[1];
            result.setImage(Base64.getDecoder().decode(imageBase64));
        }
        result.setAge(dto.getAge());
        result.setPhone(dto.getPhone());
        result.setEmail(dto.getEmail());
        result.setAddress(dto.getAddress());
        result.setDegree(dto.getDegree());
        result.setStartDate(dto.getStartDate());
        result.setEndDate(dto.getEndDate());
        result.setCreateAt(dto.getCreateAt());
        result.setStatus(dto.getStatus());
        if (dto.getSchool_id() != null) {
            result.setSchoolEntity(schoolRepository.findById(dto.getSchool_id()).get());
        }
        if (dto.getRole_id() != null) {
            result.setRoleEntity(roleRepository.findById(dto.getRole_id()).get());
        }
        if (dto.getLevelEmployee_id() != null) {
            result.setLevelEmployeeEntity(levelEmployeeRepository.findById(dto.getLevelEmployee_id()).get());
        }
        return result;
    }
}
