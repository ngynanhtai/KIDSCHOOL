package com.example.backend.service.impl;

import com.example.backend.converter.TypeSubjectConverter;
import com.example.backend.convertresponse.TypeSubjectConverterRes;
import com.example.backend.dto.AnnouncementDTO;
import com.example.backend.dto.TypeSubjectDTO;
import com.example.backend.dtoresponse.TypeSubjectDTORes;
import com.example.backend.model.AnnouncementEntity;
import com.example.backend.model.TypeSubjectEntity;
import com.example.backend.repository.TypeSubjectRepository;
import com.example.backend.service.ITypeSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypeSubjectService implements ITypeSubjectService {

    @Autowired
    private TypeSubjectRepository typeSubjectRepository;


    @Autowired
    private TypeSubjectConverter typeSubjectConverter;

    @Autowired
    private TypeSubjectConverterRes typeSubjectConverterRes;

    @Override
    public TypeSubjectDTORes findOne(Long id) {
        Optional<TypeSubjectEntity> data = typeSubjectRepository.findById(id);
        return data.isPresent()
                ? typeSubjectConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public TypeSubjectDTORes create(TypeSubjectDTO typeSubjectDTO) {
        return typeSubjectConverterRes.toDTO(typeSubjectRepository.save(typeSubjectConverter.toEntity(typeSubjectDTO)));
    }

    @Override
    public void delete(Long id) {
        typeSubjectRepository.deleteById(id);
    }

    @Override
    public TypeSubjectDTORes update(TypeSubjectDTO typeSubjectDTO) {
        TypeSubjectEntity curr = typeSubjectRepository.findById(typeSubjectDTO.getId()).get();
        TypeSubjectEntity entity = typeSubjectConverter.toEntity(curr, typeSubjectDTO);
        return typeSubjectConverterRes.toDTO(typeSubjectRepository.save(entity));
    }

    @Override
    public List<TypeSubjectDTORes> findAll() {
        List<TypeSubjectDTORes> result = new ArrayList<>();
        List<TypeSubjectEntity> entityList = typeSubjectRepository.findAll();
        for(TypeSubjectEntity item : entityList){
            result.add(typeSubjectConverterRes.toDTO(item));
        }
        return result;
    }
}
