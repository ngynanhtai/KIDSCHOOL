package com.example.backend.service;

import com.example.backend.dto.TypeClassDTO;
import com.example.backend.dtoresponse.TypeClassDTORes;
import com.example.backend.model.RoleEntity;
import com.example.backend.model.TypeClassEntity;

import java.util.List;

public interface ITypeClassService {
    TypeClassDTORes findOne(Long id);

    TypeClassDTORes create(TypeClassDTO typeClassDTO);

    void delete(Long id);

    TypeClassDTORes update(TypeClassDTO typeClassDTO);

    List<TypeClassDTORes> findAll();
}
