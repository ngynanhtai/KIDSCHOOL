package com.example.backend.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Score")
public class ScoreEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private float score;

    private String note;

    private Boolean status;

    @ManyToOne
    private SubjectEntity subjectEntity;

    @ManyToOne
    private StudentEntity studentEntity;

    @ManyToOne
    private EmployeeEntity employeeEntity;

}
