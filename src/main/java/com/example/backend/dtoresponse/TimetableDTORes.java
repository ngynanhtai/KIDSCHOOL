package com.example.backend.dtoresponse;

import com.example.backend.dto.ClassDTO;
import com.example.backend.dto.SubjectDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimetableDTORes {
    private Long id;

    private float startHour;

    private float endHour;

    private String dateStudy;

    private String note;

    private Boolean status;

    private SubjectDTO subjectDTO;

    private ClassDTO classDTO;

    private String dayOfWeek;

}
