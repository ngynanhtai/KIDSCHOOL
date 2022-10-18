package com.example.backend.dtoresponse;

import com.example.backend.dto.EmployeeDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LevelEmployeeDTORes {

    private Long id;

    private int exp;

    private Double otIncome;

    private Double allowance;

    private Double monthSalary;

    private List<EmployeeDTO> employeeDTOS;
}
