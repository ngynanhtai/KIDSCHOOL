package com.example.backend.service.impl;

import com.example.backend.converter.SchoolConverter;
import com.example.backend.convertresponse.SchoolConverterRes;
import com.example.backend.dto.SchoolDTO;
import com.example.backend.dtoresponse.SchoolDTORes;
import com.example.backend.model.SchoolEntity;
import com.example.backend.repository.SchoolRepository;
import com.example.backend.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolService implements ISchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private SchoolConverter schoolConverter;

    @Autowired
    private SchoolConverterRes schoolConverterRes;
    @Override
    public SchoolDTORes findOne(Long id) throws UnsupportedEncodingException {
        Optional<SchoolEntity> data = schoolRepository.findById(id);
        return data.isPresent()
                ? schoolConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public SchoolDTORes create(SchoolDTO schoolDTO) throws UnsupportedEncodingException {
        SchoolEntity schoolEntity = schoolConverter.toEntity(schoolDTO);
        schoolEntity.setStatus(false);
        return schoolConverterRes.toDTO(schoolRepository.save(schoolEntity));
    }

    @Override
    public void delete(Long id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public SchoolDTORes update(SchoolDTO schoolDTO) throws UnsupportedEncodingException {
        SchoolEntity curr = schoolRepository.findById(schoolDTO.getId()).get();
        SchoolEntity entity = schoolConverter.toEntity(curr, schoolDTO);
        return schoolConverterRes.toDTO(schoolRepository.save(entity));
    }

    @Override
    public List<SchoolDTORes> findAll() throws UnsupportedEncodingException {
        List<SchoolDTORes> result = new ArrayList<>();
        List<SchoolEntity> entityList = schoolRepository.findAll();
        for (SchoolEntity schoolEntity: entityList){
            result.add(schoolConverterRes.toDTO(schoolEntity));
        }
        return result;
    }
}
