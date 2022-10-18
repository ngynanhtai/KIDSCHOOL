package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDTO {
    private Long id;

    private float otHours;

    private float tax;

    private String note;

    private Double totalIncome;

    private String createAt;

    private Long employee_id;

    private Boolean status;

    private Double bonus;

    private int month;
}
