package com.example.backend.convertresponse;


import com.example.backend.converter.EventParticipateConverter;
import com.example.backend.converter.SchoolConverter;
import com.example.backend.dto.EventParticipateDTO;
import com.example.backend.dtoresponse.EventDTORes;
import com.example.backend.model.EventEntity;
import com.example.backend.model.EventParticipateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class EventConverterRes {

    @Autowired
    private SchoolConverter schoolConverter;

    @Autowired
    private EventParticipateConverter eventParticipateConverter;

    public EventDTORes toDTO(EventEntity eventEntity){
        EventDTORes eventDTORes = new EventDTORes();
        eventDTORes.setId(eventEntity.getId());
        eventDTORes.setTitle(eventEntity.getTitle());
        eventDTORes.setStartDate(eventEntity.getStartDate());
        eventDTORes.setEndDate(eventEntity.getEndDate());
        eventDTORes.setContent(eventEntity.getContent());
        eventDTORes.setContentMarkdown(eventEntity.getContentMarkdown());
        if(eventEntity.getImage() != null){
            String imageBase64 = "data:image/jpeg;base64," +  Base64.getEncoder().encodeToString(eventEntity.getImage());
            eventDTORes.setImage(imageBase64);
        }
        eventDTORes.setStatus(eventEntity.getStatus());
        eventDTORes.setCapacity(eventEntity.getCapacity());
        eventDTORes.setCreateAt(eventEntity.getCreateAt());
        eventDTORes.setSchoolDTO(eventEntity.getSchoolEntity() != null ? schoolConverter.toDTO(eventEntity.getSchoolEntity()) : null);
        List<EventParticipateDTO> eventParticipateDTOS = new ArrayList<>();
        if(eventEntity.getEventParticipateEntities() != null && eventEntity.getEventParticipateEntities().size() > 0){
            for (EventParticipateEntity eventParticipateEntity : eventEntity.getEventParticipateEntities()){
                eventParticipateDTOS.add(eventParticipateConverter.toDTO(eventParticipateEntity));
            }
        }
        eventDTORes.setEventParticipateDTOS(eventParticipateDTOS);
        return eventDTORes;
    }
}
