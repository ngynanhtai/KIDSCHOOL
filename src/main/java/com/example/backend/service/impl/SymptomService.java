package com.example.backend.service.impl;

import com.example.backend.converter.SymptomConverter;
import com.example.backend.convertresponse.SymptomConverterRes;
import com.example.backend.dto.SymptomDTO;
import com.example.backend.dtoresponse.SymptomDTORes;
import com.example.backend.model.SymptomEntity;
import com.example.backend.repository.SymptomRepository;
import com.example.backend.service.ISymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SymptomService implements ISymptomService {

    @Autowired
    private SymptomRepository symptomRepository;

    @Autowired
    private SymptomConverter symptomConverter;

    @Autowired
    private SymptomConverterRes symptomConverterRes;

    @Override
    public SymptomDTORes findOne(Long id) {
        Optional<SymptomEntity> data = symptomRepository.findById(id);
        return data.isPresent()
                ? symptomConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public SymptomDTORes create(SymptomDTO symptomDTO) {
        return symptomConverterRes.toDTO(symptomRepository.save(symptomConverter.toEntity(symptomDTO)));
    }

    @Override
    public void delete(Long id) {
        symptomRepository.deleteById(id);
    }

    @Override
    public SymptomDTORes update(SymptomDTO symptomDTO) {
        SymptomEntity curr = symptomRepository.findById(symptomDTO.getId()).get();
        SymptomEntity entity = symptomConverter.toEntity(curr, symptomDTO);
        return symptomConverterRes.toDTO(symptomRepository.save(entity));
    }

    @Override
    public List<SymptomDTORes> findAll() {
        List<SymptomDTORes> result = new ArrayList<>();
        List<SymptomEntity> entityList = symptomRepository.findAll();
        for (SymptomEntity symptomEntity : entityList){
            result.add(symptomConverterRes.toDTO(symptomEntity));
        }
        return result;
    }
}
