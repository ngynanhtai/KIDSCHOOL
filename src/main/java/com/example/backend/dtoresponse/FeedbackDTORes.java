package com.example.backend.dtoresponse;

import com.example.backend.dto.EmployeeDTO;
import com.example.backend.dto.SchoolDTO;
import com.example.backend.dto.StudentDTO;
import com.example.backend.dto.SubjectDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDTORes {

    private Long id;

    private String title;

    private String content;

    private Boolean isReply;

    private String replyContent;

    private String createAt;

    private StudentDTO studentDTO;

    private SubjectDTO subjectDTO;

    private EmployeeDTO employeeDTO;

    private SchoolDTO schoolDTO;
}
