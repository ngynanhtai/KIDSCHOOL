package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "HealthReport")
public class HealthReportEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String note;

    private String createAt;

    private Boolean status;

    @ManyToOne
    private SymptomEntity symptomEntity;

    @ManyToOne
    private StudentEntity studentEntity;
}
