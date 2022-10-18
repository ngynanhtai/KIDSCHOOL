package com.example.backend.service.impl;

import com.example.backend.converter.EventParticipateConverter;
import com.example.backend.convertresponse.EventParticipateConverterRes;
import com.example.backend.dto.EventParticipateDTO;
import com.example.backend.dtoresponse.EventParticipateDTORes;
import com.example.backend.model.EventParticipateEntity;
import com.example.backend.repository.EventParticipateRepository;
import com.example.backend.service.IEventParticipateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventParticipateService implements IEventParticipateService {

    private EventParticipateEntity entity;

    @Autowired
    private EventParticipateRepository eventParticipateRepository;

    @Autowired
    private EventParticipateConverter eventParticipateConverter;

    @Autowired
    private EventParticipateConverterRes eventParticipateConverterRes;

    @Override
    public EventParticipateDTORes findOne(Long id) throws UnsupportedEncodingException {
        Optional<EventParticipateEntity> data = eventParticipateRepository.findById(id);
        return data.isPresent()
                ? eventParticipateConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public EventParticipateDTORes create(EventParticipateDTO eventParticipateDTO) throws UnsupportedEncodingException {
        entity = eventParticipateConverter.toEntity(eventParticipateDTO);
        return eventParticipateConverterRes.toDTO(eventParticipateRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        eventParticipateRepository.deleteById(id);
    }

    @Override
    public EventParticipateDTORes update(EventParticipateDTO eventParticipateDTO) throws UnsupportedEncodingException {
        EventParticipateEntity currEntity = eventParticipateRepository.findById(eventParticipateDTO.getId()).get();
        entity = eventParticipateConverter.toEntity(currEntity, eventParticipateDTO);
        return eventParticipateConverterRes.toDTO(eventParticipateRepository.save(entity));
    }

    @Override
    public List<EventParticipateDTORes> findAll() throws UnsupportedEncodingException {
        List<EventParticipateDTORes> result = new ArrayList<>();
        List<EventParticipateEntity> entityList = eventParticipateRepository.findAll();
        for (EventParticipateEntity item : entityList) {
            result.add(eventParticipateConverterRes.toDTO(item));
        }
        return result;
    }
}
