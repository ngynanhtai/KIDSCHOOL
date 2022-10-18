package com.example.backend.service;

import com.example.backend.dto.ClassDTO;
import com.example.backend.dto.EmployeeDTO;
import com.example.backend.dto.StudentDTO;
import com.example.backend.dto.SubjectDTO;
import com.example.backend.dtoresponse.ClassDTORes;
import com.example.backend.dtoresponse.EmployeeDTORes;
import com.example.backend.dtoresponse.StudentDTORes;
import com.example.backend.dtoresponse.SubjectDTORes;
import com.example.backend.model.ClassEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IClassService {
    ClassDTORes findOne(Long id) throws UnsupportedEncodingException;

    ClassDTORes create(ClassDTO classDTO) throws UnsupportedEncodingException;

    void delete(Long id);

    ClassDTORes update(ClassDTO classDTO) throws UnsupportedEncodingException;
    List<ClassDTORes> findAll() throws UnsupportedEncodingException;

    List<StudentDTORes> findAllStudentByClassID(Long id) throws UnsupportedEncodingException;

    List<EmployeeDTORes> findAllEmployeeByClassID(Long id) throws UnsupportedEncodingException;

    List<SubjectDTORes> findAllSubjectByClassID(Long id) throws UnsupportedEncodingException;



    List<StudentDTORes> updateStudentForClass(Long id, List<Long> idList) throws UnsupportedEncodingException;

    List<EmployeeDTORes> updateEmployeeForClass(Long id, List<Long> idList) throws UnsupportedEncodingException;

    List<SubjectDTORes> updateSubjectForClass(Long id, List<Long> idList) throws UnsupportedEncodingException;


    List<StudentDTORes> removeStudentByClassID(Long id, List<Long> ids) throws UnsupportedEncodingException;

    List<EmployeeDTORes> removeEmployeeByClassID(Long id, List<Long> ids) throws UnsupportedEncodingException;

    List<SubjectDTORes> removeSubjectByClassID(Long id, List<Long> ids) throws UnsupportedEncodingException;
}
