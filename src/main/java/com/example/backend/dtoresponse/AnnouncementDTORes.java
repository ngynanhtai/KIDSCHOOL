package com.example.backend.dtoresponse;

import com.example.backend.dto.EmployeeDTO;
import com.example.backend.dto.RoleDTO;
import com.example.backend.dto.SchoolDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnnouncementDTORes {
    private Long id;

    private String title;

    private String content;

    private String createAt;

    private EmployeeDTO employeeDTO;

    private SchoolDTO schoolDTO;

    private List<RoleDTO> roleDTOS;

}
