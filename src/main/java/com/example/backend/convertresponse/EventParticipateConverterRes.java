package com.example.backend.convertresponse;

import com.example.backend.converter.EmployeeConverter;
import com.example.backend.converter.EventConverter;
import com.example.backend.dtoresponse.EventParticipateDTORes;
import com.example.backend.model.EventParticipateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class EventParticipateConverterRes {

    @Autowired
    private EventConverter eventConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    public EventParticipateDTORes toDTO(EventParticipateEntity eventParticipateEntity) throws UnsupportedEncodingException {
        EventParticipateDTORes eventParticipateDTORes = new EventParticipateDTORes();
        eventParticipateDTORes.setId(eventParticipateEntity.getId());
        eventParticipateDTORes.setName(eventParticipateEntity.getName());
        eventParticipateDTORes.setIsEmployee(eventParticipateEntity.getIsEmployee());
        eventParticipateDTORes.setEventDTO(eventParticipateEntity.getEventEntity() != null ? eventConverter.toDTO(eventParticipateEntity.getEventEntity()) : null);
        eventParticipateDTORes.setEmployeeDTO(eventParticipateEntity.getEmployeeEntity() != null ? employeeConverter.toDTO(eventParticipateEntity.getEmployeeEntity()) : null);
        return eventParticipateDTORes;
    }
}
