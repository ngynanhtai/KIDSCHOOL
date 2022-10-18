package com.example.backend.service;

import com.example.backend.dto.TypeSubjectDTO;
import com.example.backend.dtoresponse.TypeSubjectDTORes;
import com.example.backend.model.TypeSubjectEntity;

import java.util.List;

public interface ITypeSubjectService {
    TypeSubjectDTORes findOne(Long id);

    TypeSubjectDTORes create(TypeSubjectDTO typeSubjectDTO);

    void delete(Long id);

    TypeSubjectDTORes update(TypeSubjectDTO typeSubjectDTO);

    List<TypeSubjectDTORes> findAll();
}
