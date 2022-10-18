package com.example.backend.service;

import com.example.backend.dto.LevelClassDTO;
import com.example.backend.dtoresponse.LevelClassDTORes;
import com.example.backend.dtoresponse.LevelEmployeeDTORes;
import com.example.backend.model.LevelClassEntity;

import java.util.List;

public interface ILevelClassService {
    LevelClassDTORes findOne(Long id);

    LevelClassDTORes create(LevelClassDTO levelClassDTO);

    void delete(Long id);

    LevelClassDTORes update(LevelClassDTO levelClassDTO);

    List<LevelClassDTORes> findAll();
}
