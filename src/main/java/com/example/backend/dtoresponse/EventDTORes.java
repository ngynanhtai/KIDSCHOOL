package com.example.backend.dtoresponse;

import com.example.backend.dto.EventParticipateDTO;
import com.example.backend.dto.SchoolDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EventDTORes {
    private Long id;

    private String title;

    private String content;

    private String startDate;

    private String endDate;

    private Boolean status;

    private int capacity;

    private String createAt;

    private SchoolDTO schoolDTO;

    private List<EventParticipateDTO> eventParticipateDTOS;

    private String image;

    private String contentMarkdown;
}
