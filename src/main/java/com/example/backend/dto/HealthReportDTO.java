package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthReportDTO {
    private Long id;

    private String note;

    private String createAt;

    private Boolean status;

    private Long symptom_id;

    private Long student_id;
}
