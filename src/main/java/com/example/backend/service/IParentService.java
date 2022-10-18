package com.example.backend.service;

import com.example.backend.dto.ParentDTO;
import com.example.backend.dtoresponse.ParentDTORes;
import com.example.backend.model.ParentEntity;

import java.util.List;

public interface IParentService {
    ParentDTORes findOne(Long id);

    ParentDTORes create(ParentDTO parentDTO);

    void delete(Long id);

    ParentDTORes update(ParentDTO parentDTO);

    List<ParentDTORes> findAll();
}
