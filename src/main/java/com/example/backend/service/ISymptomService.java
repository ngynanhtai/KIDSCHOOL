package com.example.backend.service;

import com.example.backend.dto.SymptomDTO;
import com.example.backend.dtoresponse.SymptomDTORes;
import com.example.backend.model.SymptomEntity;

import java.util.List;

public interface ISymptomService {
    SymptomDTORes findOne(Long id);

    SymptomDTORes create(SymptomDTO symptomDTO);

    void delete(Long id);

    SymptomDTORes update(SymptomDTO symptomDTO);

    List<SymptomDTORes> findAll();
}
