package com.example.backend.service;

import com.example.backend.dto.JobApplicationDTO;
import com.example.backend.dtoresponse.JobApplicationDTORes;
import com.example.backend.model.JobApplicationEntity;
import com.example.backend.model.RoleEntity;

import java.util.List;

public interface IJobApplicationService {
    JobApplicationDTORes findOne(Long id);

    JobApplicationDTORes create(JobApplicationDTO jobApplicationDTO);

    void delete(Long id);

    JobApplicationDTORes update(JobApplicationDTO jobApplicationDTO);

    List<JobApplicationDTORes> findAll();
}
