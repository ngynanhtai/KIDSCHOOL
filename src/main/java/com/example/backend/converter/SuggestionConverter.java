package com.example.backend.converter;

import com.example.backend.dto.SuggestionDTO;
import com.example.backend.model.SuggestionEntity;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SuggestionConverter{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    public SuggestionDTO toDTO(SuggestionEntity suggestionEntity){
        SuggestionDTO suggestionDTO = new SuggestionDTO();
        suggestionDTO.setId(suggestionEntity.getId());
        suggestionDTO.setTitle(suggestionEntity.getTitle());
        suggestionDTO.setContent(suggestionEntity.getContent());
        suggestionDTO.setIsApprove(suggestionEntity.getIsApprove());
        suggestionDTO.setCreateAt(suggestionEntity.getCreateAt());
        suggestionDTO.setEmployee_id(suggestionEntity.getEmployeeEntity() != null ? suggestionEntity.getEmployeeEntity().getId() : null);
        suggestionDTO.setSchool_id(suggestionEntity.getSchoolEntity() != null ? suggestionEntity.getSchoolEntity().getId() : null);
        return suggestionDTO;
    }

    public SuggestionEntity toEntity(SuggestionDTO suggestionDTO){
        SuggestionEntity suggestionEntity = new SuggestionEntity();
        suggestionEntity.setId(suggestionDTO.getId());
        suggestionEntity.setTitle(suggestionDTO.getTitle());
        suggestionEntity.setContent(suggestionDTO.getContent());
        suggestionEntity.setIsApprove(suggestionDTO.getIsApprove());
        suggestionEntity.setCreateAt(suggestionDTO.getCreateAt());
        suggestionEntity.setEmployeeEntity(!suggestionDTO.getEmployee_id().toString().isEmpty() ? employeeRepository.findById(suggestionDTO.getEmployee_id()).get() : null);
        suggestionEntity.setSchoolEntity(!suggestionDTO.getSchool_id().toString().isEmpty() ? schoolRepository.findById(suggestionDTO.getSchool_id()).get() : null);
        return suggestionEntity;
    }

    public SuggestionEntity toEntity(SuggestionEntity result, SuggestionDTO suggestionDTO){
        result.setId(suggestionDTO.getId());
        result.setTitle(suggestionDTO.getTitle());
        result.setContent(suggestionDTO.getContent());
        result.setIsApprove(suggestionDTO.getIsApprove());
        result.setCreateAt(suggestionDTO.getCreateAt());
        result.setEmployeeEntity(!suggestionDTO.getEmployee_id().toString().isEmpty() ? employeeRepository.findById(suggestionDTO.getEmployee_id()).get() : null);
        result.setSchoolEntity(!suggestionDTO.getSchool_id().toString().isEmpty() ? schoolRepository.findById(suggestionDTO.getSchool_id()).get() : null);
        return result;
    }
}
