package com.example.backend.dtoresponse;

import com.example.backend.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDTORes {
    private Long id;

    private float otHours;

    private float tax;

    private String note;

    private Double totalIncome;

    private String createAt;

    private EmployeeDTORes employeeDTORes;

    private Boolean status;

    private Double bonus;

    private int month;
}
