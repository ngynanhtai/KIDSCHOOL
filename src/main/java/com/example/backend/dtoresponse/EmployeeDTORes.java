package com.example.backend.dtoresponse;

import com.example.backend.dto.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.BlobType;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import java.util.List;

@Getter
@Setter
public class EmployeeDTORes {

    private Long id;

    private String accountID;

    private String password;

    private String firstName;

    private String lastName;

    private String birthday;

    private Boolean gender;

    private String image;

    private int age;

    private String phone;

    private String email;

    private String address;

    private String degree;

    private String startDate;

    private String endDate;

    private String createAt;

    private Boolean status;

    private SchoolDTO schoolDTO;

    private RoleDTO roleDTO;

    private LevelEmployeeDTO levelEmployeeDTO;

    private List<SalaryDTO> salaryDTOS;

    private List<SubjectDTO> subjectDTOS;

    private List<SuggestionDTO> suggestionDTOS;

    private List<FeedbackDTO> feedbackDTOS;

    private List<ClassDTO> classDTOS;

    private List<EventParticipateDTO> eventParticipateDTOS;

    private List<ScoreDTO> scoreDTOS;

    private List<AnnouncementDTO> announcementDTOS;
}
