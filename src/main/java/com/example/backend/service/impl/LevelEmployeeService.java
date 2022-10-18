package com.example.backend.service.impl;

import com.example.backend.converter.LevelEmployeeConverter;
import com.example.backend.convertresponse.LevelEmployeeConverterRes;
import com.example.backend.dto.HealthReportDTO;
import com.example.backend.dto.LevelEmployeeDTO;
import com.example.backend.dtoresponse.LevelEmployeeDTORes;
import com.example.backend.model.HealthReportEntity;
import com.example.backend.model.LevelEmployeeEntity;
import com.example.backend.repository.LevelEmployeeRepository;
import com.example.backend.service.ILevelEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LevelEmployeeService implements ILevelEmployeeService {

    private LevelEmployeeEntity entity;

    @Autowired
    private LevelEmployeeRepository levelEmployeeRepository;

    @Autowired
    private LevelEmployeeConverter levelEmployeeConverter;

    @Autowired
    private LevelEmployeeConverterRes levelEmployeeConverterRes;

    @Override
    public LevelEmployeeDTORes findOne(Long id) throws UnsupportedEncodingException {
        Optional<LevelEmployeeEntity> data = levelEmployeeRepository.findById(id);
        return data.isPresent()
                ? levelEmployeeConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public LevelEmployeeDTORes create(LevelEmployeeDTO levelEmployeeDTO) throws UnsupportedEncodingException {
        entity = levelEmployeeConverter.toEntity(levelEmployeeDTO);
        return levelEmployeeConverterRes.toDTO(levelEmployeeRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        levelEmployeeRepository.deleteById(id);
    }

    @Override
    public LevelEmployeeDTORes update(LevelEmployeeDTO levelEmployeeDTO) throws UnsupportedEncodingException {
        LevelEmployeeEntity currEntity = levelEmployeeRepository.findById(levelEmployeeDTO.getId()).get();
        entity = levelEmployeeConverter.toEntity(currEntity, levelEmployeeDTO);
        return levelEmployeeConverterRes.toDTO(levelEmployeeRepository.save(entity));
    }

    @Override
    public List<LevelEmployeeDTORes> findAll() throws UnsupportedEncodingException {
        List<LevelEmployeeDTORes> result = new ArrayList<>();
        List<LevelEmployeeEntity> entityList = levelEmployeeRepository.findAll();
        for (LevelEmployeeEntity item : entityList) {
            result.add(levelEmployeeConverterRes.toDTO(item));
        }
        return result;
    }
}
