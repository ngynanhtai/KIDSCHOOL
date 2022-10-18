package com.example.backend.dtoresponse;

import com.example.backend.dto.EmployeeDTO;
import com.example.backend.dto.EventDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventParticipateDTORes {
    private Long id;

    private String name;

    private String phone;

    private Boolean isEmployee;

    private EventDTO eventDTO;

    private EmployeeDTO employeeDTO;
}
