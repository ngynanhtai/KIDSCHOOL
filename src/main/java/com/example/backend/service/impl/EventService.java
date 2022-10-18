package com.example.backend.service.impl;

import com.example.backend.converter.EventConverter;
import com.example.backend.convertresponse.EventConverterRes;
import com.example.backend.dto.EventDTO;
import com.example.backend.dtoresponse.EventDTORes;
import com.example.backend.model.EventEntity;
import com.example.backend.repository.EventRepository;
import com.example.backend.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService implements IEventService {

    private EventEntity entity;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventConverter eventConverter;

    @Autowired
    private EventConverterRes eventConverterRes;

    @Override
    public EventDTORes create(EventDTO eventDTO) {
        entity = eventConverter.toEntity(eventDTO);
        entity.setCreateAt(new Date().toString());
        return eventConverterRes.toDTO(eventRepository.save(entity));
    }

    @Override
    public EventDTORes findOne(Long id) {
        Optional<EventEntity> data = eventRepository.findById(id);
        return data.isPresent() ? eventConverterRes.toDTO(data.get()) : null;
    }

    @Override
    public EventDTORes update(EventDTO eventDTO) {
        EventEntity currEntity = eventRepository.findById(eventDTO.getId()).get();
        entity = eventConverter.toEntity(currEntity, eventDTO);
        return eventConverterRes.toDTO(eventRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<EventDTORes> findAll() {
        List<EventDTORes> result = new ArrayList<>();
        List<EventEntity> entityList = eventRepository.findAll();
        for (EventEntity item : entityList) {
            result.add(eventConverterRes.toDTO(item));
        }
        return result;
    }
}
