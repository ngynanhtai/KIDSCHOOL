package com.example.backend.dtoresponse;

import com.example.backend.dto.StudentDTO;
import com.example.backend.dto.SymptomDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthReportDTORes {
    private Long id;

    private String note;

    private String createAt;

    private Boolean status;

    private SymptomDTO symptomDTO;

    private StudentDTO studentDTO;
}
