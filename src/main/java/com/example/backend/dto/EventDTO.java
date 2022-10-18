package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDTO {
    private Long id;

    private String title;

    private String content;

    private String startDate;

    private String endDate;

    private Boolean status;

    private int capacity;

    private String createAt;

    private Long school_id;

    private String image;

    private String contentMarkdown;
}
