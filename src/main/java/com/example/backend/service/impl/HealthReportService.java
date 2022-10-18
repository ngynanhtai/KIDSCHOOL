package com.example.backend.service.impl;

import com.example.backend.converter.HealthReportConverter;
import com.example.backend.convertresponse.HealthReportConverterRes;
import com.example.backend.dto.HealthReportDTO;
import com.example.backend.dtoresponse.HealthReportDTORes;
import com.example.backend.model.HealthReportEntity;
import com.example.backend.repository.HealthReportRepository;
import com.example.backend.service.IHealthReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HealthReportService implements IHealthReportService {

    private HealthReportEntity entity;

    @Autowired
    private HealthReportRepository healthReportRepository;

    @Autowired
    private HealthReportConverter healthReportConverter;

    @Autowired
    private HealthReportConverterRes healthReportConverterRes;

    @Override
    public HealthReportDTORes findOne(Long id) {
        Optional<HealthReportEntity> data = healthReportRepository.findById(id);
        return data.isPresent()
                ? healthReportConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public HealthReportDTORes create(HealthReportDTO healthReportDTO) {
        entity = healthReportConverter.toEntity(healthReportDTO);
        entity.setStatus(false);
        entity.setCreateAt(new Date().toString());
        return healthReportConverterRes.toDTO(healthReportRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        healthReportRepository.deleteById(id);
    }

    @Override
    public HealthReportDTORes update(HealthReportDTO healthReportDTO) {
        HealthReportEntity currHealthReport = healthReportRepository.findById(healthReportDTO.getId()).get();
        entity = healthReportConverter.toEntity(currHealthReport, healthReportDTO);
        return healthReportConverterRes.toDTO(healthReportRepository.save(entity));
    }

    @Override
    public List<HealthReportDTORes> findAll() {
        List<HealthReportDTORes> result = new ArrayList<>();
        List<HealthReportEntity> entityList = healthReportRepository.findAll();
        for (HealthReportEntity item : entityList) {
            result.add(healthReportConverterRes.toDTO(item));
        }
        return result;
    }
}
