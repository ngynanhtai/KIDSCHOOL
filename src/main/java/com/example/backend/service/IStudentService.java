package com.example.backend.service;

import com.example.backend.dto.StudentDTO;
import com.example.backend.dto.etc.AccountDTO;
import com.example.backend.dto.etc.ChangePasswordDTO;
import com.example.backend.dtoresponse.ClassDTORes;
import com.example.backend.dtoresponse.StudentDTORes;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface IStudentService {
    StudentDTORes findOne(Long id) throws UnsupportedEncodingException;
    StudentDTORes create(StudentDTO studentDTO) throws UnsupportedEncodingException;
    void delete(Long id);
    StudentDTORes update(StudentDTO studentDTO) throws UnsupportedEncodingException;
    List<StudentDTORes> findAll() throws UnsupportedEncodingException;

    Boolean checkAccountId(String accountId);
    List<ClassDTORes> getAllClassByStudentID(Long id) throws UnsupportedEncodingException;

    List<ClassDTORes> addClassForStudent(Long id, List<Long> ids) throws UnsupportedEncodingException;

    List<ClassDTORes> removeClassForStudent(Long id, List<Long> ids) throws UnsupportedEncodingException;

    Boolean changePassword(Long id, ChangePasswordDTO changePasswordDTO);

    Map<String, String> forgotPassword(String username);

    String generateRandomString();

    void forgotPasswordChange(AccountDTO accountDTO);
}
