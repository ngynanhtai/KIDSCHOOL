package com.example.backend.dtoresponse;

import com.example.backend.dto.EmployeeDTO;
import com.example.backend.dto.SchoolDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionDTORes {
    private Long id;

    private String title;

    private String content;

    private Boolean isApprove;

    private String createAt;

    private EmployeeDTO employeeDTO;

    private SchoolDTO schoolDTO;
}
