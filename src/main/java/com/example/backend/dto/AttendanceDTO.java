package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceDTO {
    private Long id;

    private String attendanceDate;

    private String studentName;

    private String note;

    private Long student_id;

    private Long class_id;
}
