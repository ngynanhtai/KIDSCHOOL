package com.example.backend.converter;

import com.example.backend.dto.JobApplicationDTO;
import com.example.backend.model.JobApplicationEntity;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobApplicationConverter {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    public JobApplicationDTO toDTO(JobApplicationEntity entity) {
        JobApplicationDTO result = new JobApplicationDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setPhone(entity.getPhone());
        result.setCv(entity.getCv());
        result.setEmail(entity.getEmail());
        result.setStatus(entity.getStatus());
        result.setIsApprove(entity.getIsApprove());
        result.setIsInterview(entity.getIsInterview());
        result.setIsReply(entity.getIsReply());
        if (entity.getRoleEntity() != null) {
            result.setRole_id(entity.getRoleEntity().getId());
        }
        if (entity.getSchoolEntity() != null) {
            result.setSchool_id(entity.getSchoolEntity().getId());
        }
        return result;
    }

    public JobApplicationEntity toEntity(JobApplicationDTO dto) {
        JobApplicationEntity result = new JobApplicationEntity();
        result.setId(dto.getId());
        result.setName(dto.getName());
        result.setPhone(dto.getPhone());
        result.setCv(dto.getCv());
        result.setEmail(dto.getEmail());
        result.setStatus(dto.getStatus());
        result.setIsApprove(dto.getIsApprove());
        result.setIsInterview(dto.getIsInterview());
        result.setIsReply(dto.getIsReply());
        if (dto.getRole_id() != null) {
            result.setRoleEntity(roleRepository.findById(dto.getRole_id()).get());
        }
        if (dto.getSchool_id() != null) {
            result.setSchoolEntity(schoolRepository.findById(dto.getSchool_id()).get());
        }
        return result;
    }

    public JobApplicationEntity toEntity(JobApplicationEntity result, JobApplicationDTO dto) {
        result.setName(dto.getName());
        result.setPhone(dto.getPhone());
        result.setCv(dto.getCv());
        result.setEmail(dto.getEmail());
        result.setStatus(dto.getStatus());
        result.setIsApprove(dto.getIsApprove());
        result.setIsInterview(dto.getIsInterview());
        result.setIsReply(dto.getIsReply());
        if (dto.getRole_id() != null) {
            result.setRoleEntity(roleRepository.findById(dto.getRole_id()).get());
        }
        if (dto.getSchool_id() != null) {
            result.setSchoolEntity(schoolRepository.findById(dto.getSchool_id()).get());
        }
        return result;
    }
}
