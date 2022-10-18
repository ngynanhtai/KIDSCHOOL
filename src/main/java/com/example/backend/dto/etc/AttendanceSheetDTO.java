package com.example.backend.dto.etc;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AttendanceSheetDTO {
    private Long class_id;
    private String date;
    private List<ClassMemberDTO> classMember;
}
