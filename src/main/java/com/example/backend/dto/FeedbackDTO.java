package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDTO {

    private Long id;

    private String title;

    private String content;

    private Boolean isReply;

    private String replyContent;

    private String createAt;

    private Long student_id;

    private Long subject_id;

    private Long emp_id;

    private Long school_id;
}
