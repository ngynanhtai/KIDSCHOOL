package com.example.backend.dtoresponse;

import com.example.backend.dto.AnnouncementDTO;
import com.example.backend.dto.EmployeeDTO;
import com.example.backend.dto.JobApplicationDTO;
import com.example.backend.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTORes {
    private Long id;

    private String name;

    private List<EmployeeDTO> employeeDTOS;

    private List<StudentDTO> studentDTOS;

    private List<JobApplicationDTO> jobApplicationDTOS;

    private List<AnnouncementDTO> announcementDTOS;
}
