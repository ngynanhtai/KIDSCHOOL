package com.example.backend.dtoresponse;

import com.example.backend.dto.ClassDTO;
import com.example.backend.dto.SchoolDTO;
import com.example.backend.dto.StudentDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinClassDTORes {

    private Long id;

    private String joinDate;

    private Double fee;

    private String note;

    private Boolean status;

    private StudentDTO studentDTO;

    private ClassDTO classDTO;

    private SchoolDTO schoolDTO;
}
