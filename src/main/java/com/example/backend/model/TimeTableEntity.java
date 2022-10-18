package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Timetable")
public class TimeTableEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private float startHour;

    private float endHour;

    private String dateStudy;

    private String note;

    private Boolean status;

    @ManyToOne
    private SubjectEntity subjectEntity;

    @ManyToOne
    private ClassEntity classEntity;

    private String dayOfWeek;

}
