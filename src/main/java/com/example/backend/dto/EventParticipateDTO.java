package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventParticipateDTO {
    private Long id;

    private String name;

    private String phone;

    private Boolean isEmployee;

    private Long event_id;

    private Long emp_id;
}
