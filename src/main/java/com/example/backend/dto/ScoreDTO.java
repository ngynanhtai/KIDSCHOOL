package com.example.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {
    private Long id;

    private float score;

    private String note;

    private Boolean status;

    private Long subject_id;

    private Long student_id;

    private Long employee_id;
}
