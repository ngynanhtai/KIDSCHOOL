package com.example.backend.service;

import com.example.backend.dto.FeedbackDTO;
import com.example.backend.dtoresponse.FeedbackDTORes;
import com.example.backend.model.FeedbackEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IFeedbackService {
    FeedbackDTORes findOne(Long id) throws UnsupportedEncodingException;

    FeedbackDTORes create(FeedbackDTO feedbackDTO) throws UnsupportedEncodingException;

    void delete(Long id);

    FeedbackDTORes update(FeedbackDTO feedbackDTO) throws UnsupportedEncodingException;

    List<FeedbackDTORes> findAll() throws UnsupportedEncodingException;
}
