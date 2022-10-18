package com.example.backend.service.impl;

import com.example.backend.converter.ParentConverter;
import com.example.backend.convertresponse.ParentConverterRes;
import com.example.backend.dto.ParentDTO;
import com.example.backend.dtoresponse.ParentDTORes;
import com.example.backend.model.ParentEntity;
import com.example.backend.repository.ParentRepository;
import com.example.backend.service.IParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParentService implements IParentService {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private ParentConverter parentConverter;

    @Autowired
    private ParentConverterRes parentConverterRes;

    @Override
    public ParentDTORes findOne(Long id) {
        Optional<ParentEntity> data = parentRepository.findById(id);
        return data.isPresent()
                ? parentConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public ParentDTORes create(ParentDTO parentDTO) {
        return parentConverterRes.toDTO(parentRepository.save(parentConverter.toEntity(parentDTO)));
    }

    @Override
    public void delete(Long id) {
        parentRepository.deleteById(id);
    }

    @Override
    public ParentDTORes update(ParentDTO parentDTO) {
        ParentEntity curr = parentRepository.findById(parentDTO.getId()).get();
        ParentEntity entity = parentConverter.toEntity(curr, parentDTO);
        return parentConverterRes.toDTO(parentRepository.save(entity));
    }

    @Override
    public List<ParentDTORes> findAll() {
        List<ParentDTORes> result = new ArrayList<>();
        List<ParentEntity> entityList = parentRepository.findAll();
        for(ParentEntity parentEntity : entityList){
            result.add(parentConverterRes.toDTO(parentEntity));
        }
        return result;
    }
}
