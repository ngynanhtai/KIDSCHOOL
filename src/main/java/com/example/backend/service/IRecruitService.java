package com.example.backend.service;

import com.example.backend.dto.RecruitDTO;
import com.example.backend.dtoresponse.RecruitDTORes;
import com.example.backend.model.RecruitEntity;
import com.example.backend.model.RoleEntity;

import java.util.List;

public interface IRecruitService {
    RecruitDTORes findOne(Long id);

    RecruitDTORes create(RecruitDTO recruitDTO);

    void delete(Long id);

    RecruitDTORes update(RecruitDTO recruitDTO);

    List<RecruitDTORes> findAll();
}
