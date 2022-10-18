package com.example.backend.dtoresponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitDTORes {
    private Long id;

    private String title;

    private String content;

    private String contentMarkdown;
}
