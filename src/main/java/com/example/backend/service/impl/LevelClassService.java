package com.example.backend.service.impl;

import com.example.backend.converter.LevelClassConverter;
import com.example.backend.convertresponse.LevelClassConverterRes;
import com.example.backend.convertresponse.LevelEmployeeConverterRes;
import com.example.backend.dto.LevelClassDTO;
import com.example.backend.dtoresponse.LevelClassDTORes;
import com.example.backend.dtoresponse.LevelEmployeeDTORes;
import com.example.backend.model.LevelClassEntity;
import com.example.backend.repository.LevelClassRepository;
import com.example.backend.service.ILevelClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LevelClassService implements ILevelClassService {

    private LevelClassEntity entity;

    @Autowired
    private LevelClassRepository levelClassRepository;

    @Autowired
    private LevelClassConverter levelClassConverter;

    @Autowired
    private LevelClassConverterRes levelClassConverterRes;

    @Override
    public LevelClassDTORes findOne(Long id) {
        Optional<LevelClassEntity> data = levelClassRepository.findById(id);
        return data.isPresent()
                ? levelClassConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public LevelClassDTORes create(LevelClassDTO levelClassDTO) {
        entity = levelClassConverter.toEntity(levelClassDTO);
        return levelClassConverterRes.toDTO(levelClassRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        levelClassRepository.deleteById(id);
    }

    @Override
    public LevelClassDTORes update(LevelClassDTO levelClassDTO) {
        LevelClassEntity currEntity = levelClassRepository.findById(levelClassDTO.getId()).get();
        entity = levelClassConverter.toEntity(currEntity, levelClassDTO);
        return levelClassConverterRes.toDTO(levelClassRepository.save(entity));
    }

    @Override
    public List<LevelClassDTORes> findAll() {
        List<LevelClassDTORes> result = new ArrayList<>();
        List<LevelClassEntity> entityList = levelClassRepository.findAll();
        for (LevelClassEntity item : entityList) {
            result.add(levelClassConverterRes.toDTO(item));
        }
        return result;
    }
}
