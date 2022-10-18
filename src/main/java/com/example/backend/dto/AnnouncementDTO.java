package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnnouncementDTO {
    private Long id;

    private String title;

    private String content;

    private String createAt;

    private Long emp_id;

    private Long school_id;

    private List<Long> roleId_list;
}
