package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionDTO {
    private Long id;

    private String title;

    private String content;

    private Boolean isApprove;

    private String createAt;

    private Long employee_id;

    private Long school_id;
}
