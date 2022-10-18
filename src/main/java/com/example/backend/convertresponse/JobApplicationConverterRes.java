package com.example.backend.convertresponse;


import com.example.backend.converter.RoleConverter;
import com.example.backend.converter.SchoolConverter;
import com.example.backend.dtoresponse.JobApplicationDTORes;
import com.example.backend.model.JobApplicationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobApplicationConverterRes {

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private SchoolConverter schoolConverter;

    public JobApplicationDTORes toDTO(JobApplicationEntity jobApplicationEntity){
        JobApplicationDTORes jobApplicationDTORes = new JobApplicationDTORes();
        jobApplicationDTORes.setId(jobApplicationEntity.getId());
        jobApplicationDTORes.setName(jobApplicationEntity.getName());
        jobApplicationDTORes.setPhone(jobApplicationEntity.getPhone());
        jobApplicationDTORes.setCv(jobApplicationEntity.getCv());
        jobApplicationDTORes.setIsReply(jobApplicationEntity.getIsReply());
        jobApplicationDTORes.setIsInterview(jobApplicationEntity.getIsInterview());
        jobApplicationDTORes.setIsApprove(jobApplicationEntity.getIsApprove());
        jobApplicationDTORes.setStatus(jobApplicationEntity.getStatus());
        jobApplicationDTORes.setRoleDTO(jobApplicationEntity.getRoleEntity() != null ? roleConverter.toDTO(jobApplicationEntity.getRoleEntity()) : null);
        jobApplicationDTORes.setSchoolDTO(jobApplicationEntity.getSchoolEntity() != null ? schoolConverter.toDTO(jobApplicationEntity.getSchoolEntity()) : null);
        return jobApplicationDTORes;
    }
}
