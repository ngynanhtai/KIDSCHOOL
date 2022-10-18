package com.example.backend.service.impl;

import com.example.backend.converter.SubjectConverter;
import com.example.backend.convertresponse.ClassConverterRes;
import com.example.backend.convertresponse.SubjectConverterRes;
import com.example.backend.dto.SubjectDTO;
import com.example.backend.dtoresponse.ClassDTORes;
import com.example.backend.dtoresponse.SubjectDTORes;
import com.example.backend.model.ClassEntity;
import com.example.backend.model.SubjectEntity;
import com.example.backend.repository.ClassRepository;
import com.example.backend.repository.SubjectRepository;
import com.example.backend.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService implements ISubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectConverter subjectConverter;

    @Autowired
    private SubjectConverterRes subjectConverterRes;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ClassConverterRes classConverterRes;

    @Override
    public SubjectDTORes findOne(Long id) throws UnsupportedEncodingException {
        Optional<SubjectEntity> data = subjectRepository.findById(id);
        return data.isPresent()
                ? subjectConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public SubjectDTORes create(SubjectDTO subjectDTO) throws UnsupportedEncodingException {
        SubjectEntity subjectEntity = subjectConverter.toEntity(subjectDTO);
        return subjectConverterRes.toDTO(subjectRepository.save(subjectEntity));
    }

    @Override
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public SubjectDTORes update(SubjectDTO subjectDTO) throws UnsupportedEncodingException {
        SubjectEntity curr = subjectRepository.findById(subjectDTO.getId()).get();
        SubjectEntity entity = subjectConverter.toEntity(curr, subjectDTO);
        return subjectConverterRes.toDTO(subjectRepository.save(entity));
    }

    @Override
    public List<SubjectDTORes> findAll() throws UnsupportedEncodingException {
        List<SubjectDTORes> result = new ArrayList<>();
        List<SubjectEntity> entityList = subjectRepository.findAll();
        for (SubjectEntity subjectEntity : entityList){
            result.add(subjectConverterRes.toDTO(subjectEntity));
        }
        return result;
    }

    @Override
    public List<ClassDTORes> getClassesBySubjectID(Long id) throws UnsupportedEncodingException {
        Optional<SubjectEntity> data = subjectRepository.findById(id);
        if(!data.isPresent()){
            return null;
        }
        SubjectEntity curr = data.get();
        if(curr.getClassEntities().size() == 0){
            return new ArrayList<>();
        }
        List<ClassDTORes> result = new ArrayList<>();
        for (ClassEntity classEntity : curr.getClassEntities()){
            result.add(classConverterRes.toDTO(classEntity));
        }
        return result;
    }

    @Override
    public List<ClassDTORes> addClassesBySubjectID(Long id, List<Long> ids) throws UnsupportedEncodingException {
        Optional<SubjectEntity> data = subjectRepository.findById(id);
        if(!data.isPresent()){
            return null;
        }
        SubjectEntity curr = data.get();
        for (Long classID: ids){
            ClassEntity classEntity = classRepository.findById(classID).get();
            classEntity.getSubjectEntities().add(curr);
            classRepository.save(classEntity);
        }
        SubjectEntity subjectEntity = subjectRepository.findById(id).get();
        List<ClassDTORes> result = new ArrayList<>();
        for (ClassEntity classEntity : subjectEntity.getClassEntities()){
            result.add(classConverterRes.toDTO(classEntity));
        }
        return result;
    }

    @Override
    public List<ClassDTORes> removeClassesBySubjectID(Long id, List<Long> ids) throws UnsupportedEncodingException {
        Optional<SubjectEntity> data = subjectRepository.findById(id);
        if(!data.isPresent()){
            return null;
        }
        SubjectEntity curr = data.get();
        for (Long classID: ids){
            ClassEntity classEntity = classRepository.findById(classID).get();
            classEntity.getSubjectEntities().remove(curr);
            classRepository.save(classEntity);
        }
        SubjectEntity subjectEntity = subjectRepository.findById(id).get();
        List<ClassDTORes> result = new ArrayList<>();
        for (ClassEntity classEntity : subjectEntity.getClassEntities()){
            result.add(classConverterRes.toDTO(classEntity));
        }
        return result;
    }
}
