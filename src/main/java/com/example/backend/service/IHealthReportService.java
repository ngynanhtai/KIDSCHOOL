package com.example.backend.service;

import com.example.backend.dto.HealthReportDTO;
import com.example.backend.dtoresponse.HealthReportDTORes;

import java.util.List;

public interface IHealthReportService {
    HealthReportDTORes findOne(Long id);

    HealthReportDTORes create(HealthReportDTO healthReportDTO);

    void delete(Long id);

    HealthReportDTORes update(HealthReportDTO healthReportDTO);

    List<HealthReportDTORes> findAll();
}
