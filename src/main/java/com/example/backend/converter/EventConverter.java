package com.example.backend.converter;

import com.example.backend.dto.EventDTO;
import com.example.backend.model.EventEntity;
import com.example.backend.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class EventConverter {

    @Autowired
    private SchoolRepository schoolRepository;

    public EventDTO toDTO(EventEntity entity) {
        EventDTO result = new EventDTO();
        result.setId(entity.getId());
        result.setCreateAt(entity.getCreateAt());
        result.setStatus(entity.getStatus());
        result.setContent(entity.getContent());
        result.setTitle(entity.getTitle());
        result.setCapacity(entity.getCapacity());
        result.setContentMarkdown(entity.getContentMarkdown());
        result.setStartDate(entity.getStartDate());
        if(entity.getImage() != null){
            String imageBase64 = "data:image/jpeg;base64, " +  Base64.getEncoder().encodeToString(entity.getImage());
            result.setImage(imageBase64);
        }
        result.setEndDate(entity.getEndDate());
        if (entity.getSchoolEntity() != null) {
            result.setSchool_id(entity.getSchoolEntity().getId());
        }
        return result;
    }

    public EventEntity toEntity(EventDTO dto) {
        EventEntity result = new EventEntity();
        result.setId(dto.getId());
        result.setCreateAt(dto.getCreateAt());
        result.setStatus(dto.getStatus());
        result.setContent(dto.getContent());
        result.setTitle(dto.getTitle());
        result.setContentMarkdown(dto.getContentMarkdown());
        if(dto.getImage() != null){
            String imageBase64 = dto.getImage().split(",")[1];
            result.setImage(Base64.getDecoder().decode(imageBase64));
        }
        result.setCapacity(dto.getCapacity());
        result.setStartDate(dto.getStartDate());
        result.setEndDate(dto.getEndDate());
        if (dto.getSchool_id() != null) {
            result.setSchoolEntity(schoolRepository.findById(dto.getSchool_id()).get());
        }
        return result;
    }

    public EventEntity toEntity(EventEntity result, EventDTO dto) {
        result.setCreateAt(dto.getCreateAt());
        result.setStatus(dto.getStatus());
        result.setContent(dto.getContent());
        if(dto.getImage() != null){
            String imageBase64 = dto.getImage().split(",")[1];
            result.setImage(Base64.getDecoder().decode(imageBase64));
        }
        result.setTitle(dto.getTitle());
        result.setCapacity(dto.getCapacity());
        result.setStartDate(dto.getStartDate());
        result.setEndDate(dto.getEndDate());
        result.setContentMarkdown(dto.getContentMarkdown());
        if (dto.getSchool_id() != null) {
            result.setSchoolEntity(schoolRepository.findById(dto.getSchool_id()).get());
        }
        return result;
    }
}
