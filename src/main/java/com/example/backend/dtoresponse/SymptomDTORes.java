package com.example.backend.dtoresponse;

import com.example.backend.dto.HealthReportDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SymptomDTORes {
    private Long id;

    private String name;

    private List<HealthReportDTO> healthReportDTOS;
}
