package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Attendance")
public class AttendanceEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String attendanceDate;

    private String studentName;

    private String note;

    @ManyToOne
    private StudentEntity studentEntity;

    @ManyToOne
    private ClassEntity classEntity;

}
