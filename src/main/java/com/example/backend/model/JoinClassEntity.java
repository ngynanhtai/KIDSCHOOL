package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "JoinClass")
public class JoinClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String joinDate;

    private Double fee;

    private String note;

    private Boolean status;

    @ManyToOne
    private StudentEntity studentEntity;

    @ManyToOne
    private ClassEntity classEntity;

    @ManyToOne
    private SchoolEntity schoolEntity;

}
