package com.example.backend.service;

import com.example.backend.dto.EventParticipateDTO;
import com.example.backend.dtoresponse.EventParticipateDTORes;
import com.example.backend.model.EventParticipateEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IEventParticipateService {
    EventParticipateDTORes findOne(Long id) throws UnsupportedEncodingException;

    EventParticipateDTORes create(EventParticipateDTO eventParticipateDTO) throws UnsupportedEncodingException;

    void delete(Long id);

    EventParticipateDTORes update(EventParticipateDTO eventParticipateDTO) throws UnsupportedEncodingException;

    List<EventParticipateDTORes> findAll() throws UnsupportedEncodingException;
}
