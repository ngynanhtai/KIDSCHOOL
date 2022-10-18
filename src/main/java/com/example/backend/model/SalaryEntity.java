package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Salary")
public class SalaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float otHours;

    private float tax;

    private String note;

    private Double totalIncome;

    private String createAt;

    private Boolean status;

    private int month;

    private Double bonus;

    @ManyToOne
    private EmployeeEntity employeeEntity;

}
