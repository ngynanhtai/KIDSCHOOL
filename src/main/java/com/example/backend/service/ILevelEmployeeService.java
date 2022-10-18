package com.example.backend.service;

import com.example.backend.dto.LevelEmployeeDTO;
import com.example.backend.dtoresponse.LevelEmployeeDTORes;
import com.example.backend.model.LevelEmployeeEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ILevelEmployeeService {
    LevelEmployeeDTORes findOne(Long id) throws UnsupportedEncodingException;

    LevelEmployeeDTORes create(LevelEmployeeDTO levelEmployeeDTO) throws UnsupportedEncodingException;

    void delete(Long id);

    LevelEmployeeDTORes update(LevelEmployeeDTO levelEmployeeDTO) throws UnsupportedEncodingException;

    List<LevelEmployeeDTORes> findAll() throws UnsupportedEncodingException;
}
