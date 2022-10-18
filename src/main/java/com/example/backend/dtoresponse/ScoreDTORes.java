package com.example.backend.dtoresponse;


import com.example.backend.dto.EmployeeDTO;
import com.example.backend.dto.StudentDTO;
import com.example.backend.dto.SubjectDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTORes {
    private Long id;

    private float score;

    private String note;

    private Boolean status;

    private SubjectDTO subjectDTO;

    private StudentDTO studentDTO;

    private EmployeeDTO employeeDTO;
}
