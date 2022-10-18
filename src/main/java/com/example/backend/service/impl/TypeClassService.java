package com.example.backend.service.impl;

import com.example.backend.converter.TypeClassConverter;
import com.example.backend.convertresponse.TypeClassConverterRes;
import com.example.backend.dto.TypeClassDTO;
import com.example.backend.dtoresponse.TypeClassDTORes;
import com.example.backend.model.TypeClassEntity;
import com.example.backend.model.TypeSubjectEntity;
import com.example.backend.repository.TypeClassRepository;
import com.example.backend.service.ITypeClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypeClassService implements ITypeClassService {

    @Autowired
    private TypeClassRepository typeClassRepository;

    @Autowired
    private TypeClassConverter typeClassConverter;

    @Autowired
    private TypeClassConverterRes typeClassConverterRes;

    @Override
    public TypeClassDTORes findOne(Long id) {
        Optional<TypeClassEntity> data = typeClassRepository.findById(id);
        return data.isPresent()
                ? typeClassConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public TypeClassDTORes create(TypeClassDTO typeClassDTO) {
        return typeClassConverterRes.toDTO(typeClassRepository.save(typeClassConverter.toEntity(typeClassDTO)));
    }

    @Override
    public void delete(Long id) {
        typeClassRepository.deleteById(id);
    }

    @Override
    public TypeClassDTORes update(TypeClassDTO typeClassDTO) {
        TypeClassEntity curr = typeClassRepository.findById(typeClassDTO.getId()).get();
        TypeClassEntity entity = typeClassConverter.toEntity(curr, typeClassDTO);
        return typeClassConverterRes.toDTO(typeClassRepository.save(entity));
    }

    @Override
    public List<TypeClassDTORes> findAll() {
        List<TypeClassDTORes> result  = new ArrayList<>();
        List<TypeClassEntity> entityList = typeClassRepository.findAll();
        for(TypeClassEntity item : entityList){
            result.add(typeClassConverterRes.toDTO(item));
        }
        return result;
    }
}
