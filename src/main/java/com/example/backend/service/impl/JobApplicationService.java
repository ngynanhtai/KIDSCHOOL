package com.example.backend.service.impl;

import com.example.backend.converter.JobApplicationConverter;
import com.example.backend.convertresponse.JobApplicationConverterRes;
import com.example.backend.dto.JobApplicationDTO;
import com.example.backend.dtoresponse.JobApplicationDTORes;
import com.example.backend.model.JobApplicationEntity;
import com.example.backend.repository.JobApplicationRepository;
import com.example.backend.service.IJobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService implements IJobApplicationService {

    private JobApplicationEntity entity;
    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private JobApplicationConverter jobApplicationConverter;

    @Autowired
    private JobApplicationConverterRes jobApplicationConverterRes;

    @Override
    public JobApplicationDTORes findOne(Long id) {
        Optional<JobApplicationEntity> data = jobApplicationRepository.findById(id);
        return data.isPresent()
                ? jobApplicationConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public JobApplicationDTORes create(JobApplicationDTO jobApplicationDTO) {
        entity = jobApplicationConverter.toEntity(jobApplicationDTO);
        entity.setIsReply(false);
        entity.setIsInterview(false);
        entity.setIsApprove(false);
        entity.setStatus(false);
        return jobApplicationConverterRes.toDTO(jobApplicationRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        jobApplicationRepository.deleteById(id);
    }

    @Override
    public JobApplicationDTORes update(JobApplicationDTO jobApplicationDTO) {
        JobApplicationEntity currEntity = jobApplicationRepository.findById(jobApplicationDTO.getId()).get();
        entity = jobApplicationConverter.toEntity(currEntity, jobApplicationDTO);
        return jobApplicationConverterRes.toDTO(jobApplicationRepository.save(entity));
    }

    @Override
    public List<JobApplicationDTORes> findAll() {
        List<JobApplicationDTORes> result = new ArrayList<>();
        List<JobApplicationEntity> entityList = jobApplicationRepository.findAll();
        for (JobApplicationEntity item : entityList) {
            result.add(jobApplicationConverterRes.toDTO(item));
        }
        return result;
    }
}
