package com.example.backend.converter;

import com.example.backend.dto.HealthReportDTO;
import com.example.backend.model.HealthReportEntity;
import com.example.backend.repository.StudentRepository;
import com.example.backend.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HealthReportConverter {

    @Autowired
    private SymptomRepository symptomRepository;

    @Autowired
    private StudentRepository studentRepository;

    public HealthReportDTO toDTO(HealthReportEntity entity) {
        HealthReportDTO result = new HealthReportDTO();
        result.setId(entity.getId());
        result.setCreateAt(entity.getCreateAt());
        result.setStatus(entity.getStatus());
        result.setNote(entity.getNote());
        if (entity.getStudentEntity() != null) {
            result.setStudent_id(entity.getStudentEntity().getId());
        }
        if (entity.getSymptomEntity() != null) {
            result.setSymptom_id(entity.getSymptomEntity().getId());
        }
        return result;
    }

    public HealthReportEntity toEntity(HealthReportDTO dto) {
        HealthReportEntity result = new HealthReportEntity();
        result.setId(dto.getId());
        result.setCreateAt(dto.getCreateAt());
        result.setStatus(dto.getStatus());
        result.setNote(dto.getNote());
        if (dto.getStudent_id() != null) {
            result.setStudentEntity(studentRepository.findById(dto.getStudent_id()).get());
        }
        if (dto.getSymptom_id() != null) {
            result.setSymptomEntity(symptomRepository.findById(dto.getSymptom_id()).get());
        }
        return result;
    }

    public HealthReportEntity toEntity(HealthReportEntity result, HealthReportDTO dto) {
        result.setCreateAt(dto.getCreateAt());
        result.setStatus(dto.getStatus());
        result.setNote(dto.getNote());
        result.setStudentEntity(studentRepository.findById(dto.getStudent_id()).get());
        result.setSymptomEntity(symptomRepository.findById(dto.getSymptom_id()).get());
        return result;
    }
}
