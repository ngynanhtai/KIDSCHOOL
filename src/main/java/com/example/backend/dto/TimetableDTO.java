package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimetableDTO {
    private Long id;

    private float startHour;

    private float endHour;

    private String dateStudy;

    private String note;

    private Boolean status;

    private Long subject_id;

    private Long class_id;

    private String dayOfWeek;
}
