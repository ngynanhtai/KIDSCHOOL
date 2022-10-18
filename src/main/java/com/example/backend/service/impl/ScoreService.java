package com.example.backend.service.impl;

import com.example.backend.converter.ScoreConverter;
import com.example.backend.convertresponse.ScoreConverterRes;
import com.example.backend.dto.ScoreDTO;
import com.example.backend.dtoresponse.ScoreDTORes;
import com.example.backend.model.ScoreEntity;
import com.example.backend.repository.ScoreRepository;
import com.example.backend.service.IScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScoreService implements IScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private ScoreConverter scoreConverter;

    @Autowired
    private ScoreConverterRes scoreConverterRes;
    @Override
    public ScoreDTORes findOne(Long id) throws UnsupportedEncodingException {
        Optional<ScoreEntity> data = scoreRepository.findById(id);
        return data.isPresent()
                ? scoreConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public ScoreDTORes create(ScoreDTO scoreDTO) throws UnsupportedEncodingException {
        ScoreEntity scoreEntity = scoreRepository.save(scoreConverter.toEntity(scoreDTO));
        scoreEntity.setStatus(false);
        return scoreConverterRes.toDTO(scoreRepository.save(scoreEntity));
    }

    @Override
    public void delete(Long id) {
        scoreRepository.deleteById(id);
    }

    @Override
    public ScoreDTORes update(ScoreDTO scoreDTO) throws UnsupportedEncodingException {
        ScoreEntity curr = scoreRepository.findById(scoreDTO.getId()).get();
        ScoreEntity entity = scoreConverter.toEntity(curr, scoreDTO);
        return scoreConverterRes.toDTO(scoreRepository.save(entity));
    }

    @Override
    public List<ScoreDTORes> findAll() throws UnsupportedEncodingException {
        List<ScoreDTORes> result = new ArrayList<>();
        List<ScoreEntity> entityList = scoreRepository.findAll();
        for (ScoreEntity scoreEntity : entityList){
            result.add(scoreConverterRes.toDTO(scoreEntity));
        }
        return result;
    }

    @Override
    public Boolean updateScores(List<ScoreDTO> scoreDTOS) {
        try{
            for (ScoreDTO scoreDTO : scoreDTOS){
                ScoreEntity scoreEntity = scoreConverter.toEntity(scoreDTO);
                scoreRepository.save(scoreEntity);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
