package com.example.backend.dtoresponse;

import com.example.backend.dto.RoleDTO;
import com.example.backend.dto.SchoolDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobApplicationDTORes {
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String cv;

    private Boolean isReply;

    private Boolean isInterview;

    private Boolean isApprove;

    private Boolean status;

    private RoleDTO roleDTO;

    private SchoolDTO schoolDTO;
}
