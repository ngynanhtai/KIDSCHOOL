package com.example.backend.dtoresponse;

import com.example.backend.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.BlobType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTORes {
    private Long id;
    private String name;
    private Double fee;
    private Float hour;
    private String startDate;
    private String endDate;
    private Boolean status;
    private String image;


    private EmployeeDTO employeeDTO;
    private TypeSubjectDTO typeSubjectDTO;

    private List<ClassDTO> classDTOS;
    private List<FeedbackDTO> feedbackDTOS;
    private List<TimetableDTO> timetableDTOS;
    private List<ScoreDTO> scoreDTOS;
}
