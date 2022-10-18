package com.example.backend.service;

import com.example.backend.dto.SchoolDTO;
import com.example.backend.dtoresponse.SchoolDTORes;
import com.example.backend.model.SchoolEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ISchoolService {
    SchoolDTORes findOne(Long id) throws UnsupportedEncodingException;

    SchoolDTORes create(SchoolDTO schoolDTO) throws UnsupportedEncodingException;

    void delete(Long id);

    SchoolDTORes update(SchoolDTO schoolDTO) throws UnsupportedEncodingException;

    List<SchoolDTORes> findAll() throws UnsupportedEncodingException;
}
