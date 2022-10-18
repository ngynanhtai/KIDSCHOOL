package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobApplicationDTO {
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String cv;

    private Boolean isReply;

    private Boolean isInterview;

    private Boolean isApprove;

    private Boolean status;

    private Long role_id;

    private Long school_id;
}
