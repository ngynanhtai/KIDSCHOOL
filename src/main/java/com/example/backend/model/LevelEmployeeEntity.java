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
@Table(name = "LevelEmployee")
public class LevelEmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int exp;

    private Double otIncome;

    private Double allowance;

    private Double monthSalary;

    @OneToMany(mappedBy = "levelEmployeeEntity")
    private List<EmployeeEntity> employeeEntities;
}
