package com.example.backend.service;

import com.example.backend.dto.ScoreDTO;
import com.example.backend.dtoresponse.ScoreDTORes;
import com.example.backend.model.ScoreEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IScoreService {
    ScoreDTORes findOne(Long id) throws UnsupportedEncodingException;

    ScoreDTORes create(ScoreDTO scoreDTO) throws UnsupportedEncodingException;

    void delete(Long id);

    ScoreDTORes update(ScoreDTO scoreDTO) throws UnsupportedEncodingException;

    List<ScoreDTORes> findAll() throws UnsupportedEncodingException;

    Boolean updateScores(List<ScoreDTO> scoreDTOS);
}
