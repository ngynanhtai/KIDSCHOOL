package com.example.backend.service.impl;

import com.example.backend.converter.SuggestionConverter;
import com.example.backend.convertresponse.SuggestionConverterRes;
import com.example.backend.dto.SuggestionDTO;
import com.example.backend.dtoresponse.SuggestionDTORes;
import com.example.backend.model.RoleEntity;
import com.example.backend.model.SuggestionEntity;
import com.example.backend.repository.SuggestionRepository;
import com.example.backend.service.ISuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SuggestionService implements ISuggestionService {

    @Autowired
    private SuggestionRepository suggestionRepository;

    @Autowired
    private SuggestionConverter suggestionConverter;

    @Autowired
    private SuggestionConverterRes suggestionConverterRes;

    @Override
    public SuggestionDTORes findOne(Long id) throws UnsupportedEncodingException {
        Optional<SuggestionEntity> data = suggestionRepository.findById(id);
        return data.isPresent()
                ? suggestionConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public SuggestionDTORes create(SuggestionDTO suggestionDTO) throws UnsupportedEncodingException {
        SuggestionEntity suggestionEntity = suggestionConverter.toEntity(suggestionDTO);
        suggestionEntity.setIsApprove(false);
        suggestionEntity.setCreateAt(new Date().toString());
        return suggestionConverterRes.toDTO(suggestionRepository.save(suggestionEntity));
    }

    @Override
    public void delete(Long id) {
        suggestionRepository.deleteById(id);
    }

    @Override
    public SuggestionDTORes update(SuggestionDTO suggestionDTO) throws UnsupportedEncodingException {
        SuggestionEntity curr = suggestionRepository.findById(suggestionDTO.getId()).get();
        SuggestionEntity entity = suggestionConverter.toEntity(curr, suggestionDTO);
        return suggestionConverterRes.toDTO(suggestionRepository.save(entity));
    }

    @Override
    public List<SuggestionDTORes> findAll() throws UnsupportedEncodingException {
        List<SuggestionDTORes> result = new ArrayList<>();
        List<SuggestionEntity> entityList = suggestionRepository.findAll();
        for (SuggestionEntity suggestionEntity : entityList){
            result.add(suggestionConverterRes.toDTO(suggestionEntity));
        }
        return result;
    }
}
