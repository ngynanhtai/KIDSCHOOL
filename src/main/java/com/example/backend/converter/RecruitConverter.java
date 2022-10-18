package com.example.backend.converter;

import com.example.backend.dto.RecruitDTO;
import com.example.backend.model.RecruitEntity;
import org.springframework.stereotype.Component;

@Component
public class RecruitConverter {
    public RecruitDTO toDTO(RecruitEntity recruitEntity){
        RecruitDTO recruitDTO = new RecruitDTO();
        recruitDTO.setId(recruitEntity.getId());
        recruitDTO.setTitle(recruitEntity.getTitle());
        recruitDTO.setContent(recruitEntity.getContent());
        recruitDTO.setContentMarkdown(recruitEntity.getContentMarkdown());
        return recruitDTO;
    }

    public RecruitEntity toEntity(RecruitDTO recruitDTO){
        RecruitEntity recruitEntity = new RecruitEntity();
        recruitEntity.setId(recruitDTO.getId());
        recruitEntity.setTitle(recruitDTO.getTitle());
        recruitEntity.setContent(recruitDTO.getContent());
        recruitEntity.setContentMarkdown(recruitDTO.getContentMarkdown());
        return recruitEntity;
    }

    public RecruitEntity toEntity(RecruitEntity result, RecruitDTO recruitDTO){
        result.setTitle(recruitDTO.getTitle());
        result.setContent(recruitDTO.getContent());
        result.setContentMarkdown(recruitDTO.getContentMarkdown());
        return result;
    }
}
