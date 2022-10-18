package com.example.backend.dtoresponse;

import com.example.backend.dto.ClassDTO;
import com.example.backend.dto.StudentDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceDTORes {
    private Long id;

    private String attendanceDate;

    private String studentName;

    private String note;

    private StudentDTO studentDTO;

    private ClassDTO classDTO;
}
