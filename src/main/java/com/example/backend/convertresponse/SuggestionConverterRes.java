package com.example.backend.convertresponse;


import com.example.backend.converter.EmployeeConverter;
import com.example.backend.converter.SchoolConverter;
import com.example.backend.dtoresponse.SuggestionDTORes;
import com.example.backend.model.SuggestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class SuggestionConverterRes {

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private SchoolConverter schoolConverter;

    public SuggestionDTORes toDTO(SuggestionEntity suggestionEntity) throws UnsupportedEncodingException {
        SuggestionDTORes suggestionDTORes = new SuggestionDTORes();
        suggestionDTORes.setId(suggestionEntity.getId());
        suggestionDTORes.setTitle(suggestionEntity.getTitle());
        suggestionDTORes.setContent(suggestionEntity.getContent());
        suggestionDTORes.setIsApprove(suggestionEntity.getIsApprove());
        suggestionDTORes.setCreateAt(suggestionEntity.getCreateAt());
        suggestionDTORes.setEmployeeDTO(suggestionEntity.getEmployeeEntity() != null ? employeeConverter.toDTO(suggestionEntity.getEmployeeEntity()) : null);
        suggestionDTORes.setSchoolDTO(suggestionEntity.getSchoolEntity() != null ? schoolConverter.toDTO(suggestionEntity.getSchoolEntity()) : null);
        return suggestionDTORes;
    }
}
