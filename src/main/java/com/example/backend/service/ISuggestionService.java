package com.example.backend.service;

import com.example.backend.dto.SuggestionDTO;
import com.example.backend.dtoresponse.SuggestionDTORes;
import com.example.backend.model.SuggestionEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ISuggestionService {
    SuggestionDTORes findOne(Long id) throws UnsupportedEncodingException;

    SuggestionDTORes create(SuggestionDTO suggestionDTO) throws UnsupportedEncodingException;

    void delete(Long id);

    SuggestionDTORes update(SuggestionDTO suggestionDTO) throws UnsupportedEncodingException;

    List<SuggestionDTORes> findAll() throws UnsupportedEncodingException;
}
