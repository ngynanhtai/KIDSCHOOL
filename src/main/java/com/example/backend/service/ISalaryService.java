package com.example.backend.service;

import com.example.backend.dto.SalaryDTO;
import com.example.backend.dtoresponse.SalaryDTORes;
import com.example.backend.model.SalaryEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ISalaryService {
    SalaryDTORes findOne(Long id) throws UnsupportedEncodingException;

    SalaryDTORes create(SalaryDTO salaryDTO) throws UnsupportedEncodingException;

    List<SalaryDTORes> updateListSalary(List<SalaryDTO> salaryDTOList) throws UnsupportedEncodingException;

    void delete(Long id);

    SalaryDTORes update(SalaryDTO salaryDTO) throws UnsupportedEncodingException;

    List<SalaryDTORes> findAll() throws UnsupportedEncodingException;

    List<SalaryDTORes> getAllSalariesByMonth(int month) throws UnsupportedEncodingException;
}
