package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassDTO {

    private Long id;

    private String classId;

    private String name;

    private Double fee;

    private String startDate;

    private String endDate;

    private Boolean status;

    private int capacity;

    private Long school_id;

    private Long typeClass_id;

    private Long levelClass_id;

}
