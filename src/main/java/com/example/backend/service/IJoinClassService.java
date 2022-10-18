package com.example.backend.service;

import com.example.backend.dto.JoinClassDTO;
import com.example.backend.dtoresponse.JoinClassDTORes;
import com.example.backend.model.JoinClassEntity;

import java.util.List;

public interface IJoinClassService {
    JoinClassDTORes findOne(Long id);

    JoinClassDTORes create(JoinClassDTO joinClassDTO);

    void delete(Long id);

    JoinClassDTORes update(JoinClassDTO joinClassDTO);

    List<JoinClassDTORes> findAll();
}
