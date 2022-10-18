package com.example.backend.service;

import com.example.backend.dto.SubjectDTO;
import com.example.backend.dtoresponse.ClassDTORes;
import com.example.backend.dtoresponse.SubjectDTORes;
import com.example.backend.model.SubjectEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ISubjectService {
    SubjectDTORes findOne(Long id) throws UnsupportedEncodingException;

    SubjectDTORes create(SubjectDTO subjectDTO) throws UnsupportedEncodingException;

    void delete(Long id);

    SubjectDTORes update(SubjectDTO subjectDTO) throws UnsupportedEncodingException;

    List<SubjectDTORes> findAll() throws UnsupportedEncodingException;

    List<ClassDTORes> getClassesBySubjectID(Long id) throws UnsupportedEncodingException;

    List<ClassDTORes> addClassesBySubjectID(Long id, List<Long> ids) throws UnsupportedEncodingException;

    List<ClassDTORes> removeClassesBySubjectID(Long id, List<Long> ids) throws UnsupportedEncodingException;
}
