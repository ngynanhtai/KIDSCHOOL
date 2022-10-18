package com.example.backend.dtoresponse;

import com.example.backend.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDTORes {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Boolean status;

    private List<ClassDTO> classDTOS;

    private List<StudentDTO> studentDTOS;

    private List<SuggestionDTO> suggestionDTOS;

    private List<EmployeeDTO> employeeDTOS;

    private List<FeedbackDTO> feedbackDTOS;

    private List<EventDTO> eventDTOS;

    private List<JobApplicationDTO> jobApplicationDTOS;

    private List<AnnouncementDTO> announcementDTOS;

    private List<JoinClassDTO> joinClassDTOS;
}
