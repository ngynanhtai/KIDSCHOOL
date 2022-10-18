package com.example.backend.service.impl;

import com.example.backend.converter.FeedbackConverter;
import com.example.backend.convertresponse.FeedbackConverterRes;
import com.example.backend.dto.FeedbackDTO;
import com.example.backend.dtoresponse.FeedbackDTORes;
import com.example.backend.model.FeedbackEntity;
import com.example.backend.repository.FeedbackRepository;
import com.example.backend.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService implements IFeedbackService {

    private FeedbackEntity entity;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackConverter feedbackConverter;

    @Autowired
    private FeedbackConverterRes feedbackConverterRes;

    @Override
    public FeedbackDTORes findOne(Long id) throws UnsupportedEncodingException {
        Optional<FeedbackEntity> data = feedbackRepository.findById(id);
        return data.isPresent()
                ? feedbackConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public FeedbackDTORes create(FeedbackDTO feedbackDTO) throws UnsupportedEncodingException {
        entity = feedbackConverter.toEntity(feedbackDTO);
        entity.setIsReply(false);
        entity.setCreateAt(new Date().toString());
        return feedbackConverterRes.toDTO(feedbackRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public FeedbackDTORes update(FeedbackDTO feedbackDTO) throws UnsupportedEncodingException {
        FeedbackEntity currEntity = feedbackRepository.findById(feedbackDTO.getId()).get();
        entity = feedbackConverter.toEntity(currEntity, feedbackDTO);
        return feedbackConverterRes.toDTO(feedbackRepository.save(entity));
    }

    @Override
    public List<FeedbackDTORes> findAll() throws UnsupportedEncodingException {
        List<FeedbackDTORes> result = new ArrayList<>();
        List<FeedbackEntity> entityList = feedbackRepository.findAll();
        for (FeedbackEntity item : entityList) {
            result.add(feedbackConverterRes.toDTO(item));
        }
        return result;
    }
}
