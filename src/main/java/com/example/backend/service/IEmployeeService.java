package com.example.backend.service;

import com.example.backend.dto.etc.AccountDTO;
import com.example.backend.dto.EmployeeDTO;
import com.example.backend.dto.etc.ChangePasswordDTO;
import com.example.backend.dtoresponse.AccountDTORes;
import com.example.backend.dtoresponse.ClassDTORes;
import com.example.backend.dtoresponse.EmployeeDTORes;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IEmployeeService {
    EmployeeDTORes findOne(Long id) throws UnsupportedEncodingException;

    EmployeeDTORes create(EmployeeDTO employeeDTO) throws UnsupportedEncodingException;

    void delete(Long id);

    EmployeeDTORes update(EmployeeDTO employeeDTO) throws UnsupportedEncodingException;

    List<EmployeeDTORes> findAll() throws UnsupportedEncodingException;

    List<ClassDTORes> findAllClassByTeacherID(Long id) throws UnsupportedEncodingException;

    Boolean checkAccountId(String accountId);

    AccountDTORes checkLogin(AccountDTO accountDTO);

    List<ClassDTORes> addClassForTeacher(Long id, List<Long> ids) throws UnsupportedEncodingException;

    List<ClassDTORes> removeClassForTeacher(Long id, List<Long> ids) throws UnsupportedEncodingException;

    Boolean changePassword(Long id, ChangePasswordDTO changePasswordDTO);
}
