package com.example.backend.service;

import com.example.backend.dto.EventDTO;
import com.example.backend.dtoresponse.EventDTORes;
import com.example.backend.model.EventEntity;

import java.util.List;

public interface IEventService {
    EventDTORes findOne(Long id);

    EventDTORes create(EventDTO eventDTO);

    void delete(Long id);

    EventDTORes update(EventDTO eventDTO);

    List<EventDTORes> findAll();

}
