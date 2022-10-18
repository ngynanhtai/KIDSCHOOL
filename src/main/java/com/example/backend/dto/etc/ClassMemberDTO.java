package com.example.backend.dto.etc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassMemberDTO {
    private Long id;
    private String name;
    private Boolean isAbsent;
    private String note;
}
