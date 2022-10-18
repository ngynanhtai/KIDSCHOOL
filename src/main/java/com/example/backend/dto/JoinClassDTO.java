package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinClassDTO {

    private Long id;

    private String joinDate;

    private Double fee;

    private String note;

    private Boolean status;

    private Long student_id;

    private Long class_id;

    private Long school_id;
}
