package com.example.backend.converter;

import com.example.backend.dto.EventParticipateDTO;
import com.example.backend.model.EventParticipateEntity;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventParticipateConverter {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public EventParticipateDTO toDTO(EventParticipateEntity entity) {
        EventParticipateDTO result = new EventParticipateDTO();
        result.setId(entity.getId());
        result.setIsEmployee(entity.getIsEmployee());
        result.setName(entity.getName());
        result.setPhone(entity.getPhone());
        if (entity.getEventEntity() != null) {
            result.setEvent_id(entity.getEventEntity().getId());
        }
        if (entity.getEmployeeEntity() != null) {
            result.setEmp_id(entity.getEmployeeEntity().getId());
        }
        return result;
    }

    public EventParticipateEntity toEntity(EventParticipateDTO dto) {
        EventParticipateEntity result = new EventParticipateEntity();
        result.setId(dto.getId());
        result.setIsEmployee(dto.getIsEmployee());
        result.setName(dto.getName());
        result.setPhone(dto.getPhone());
        if (dto.getEvent_id() != null) {
            result.setEventEntity(eventRepository.findById(dto.getEvent_id()).get());
        }
        if (dto.getEmp_id() != null) {
            result.setEmployeeEntity(employeeRepository.findById(dto.getEmp_id()).get());
        }
        return result;
    }

    public EventParticipateEntity toEntity(EventParticipateEntity result, EventParticipateDTO dto) {
        result.setIsEmployee(dto.getIsEmployee());
        result.setName(dto.getName());
        result.setPhone(dto.getPhone());
        if (dto.getEvent_id() != null) {
            result.setEventEntity(eventRepository.findById(dto.getEvent_id()).get());
        }
        if (dto.getEmp_id() != null) {
            result.setEmployeeEntity(employeeRepository.findById(dto.getEmp_id()).get());
        }
        return result;
    }
}
