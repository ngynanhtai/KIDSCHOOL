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
public class StudentDTORes {
    private Long id;

    private String accountId;

    private String password;

    private String firstName;

    private String lastName;

    private Boolean gender;

    private String image;

    private int age;

    private String birthday;

    private String phone;

    private String email;

    private String address;

    private String startDate;

    private String endDate;

    private String createAt;

    private Boolean status;

    private RoleDTO roleDTO;

    private SchoolDTO schoolDTO;

    private ParentDTO parentDTO;

    private List<FeedbackDTO> feedbackDTOS;

    private List<HealthReportDTO> healthReportDTOS;

    private List<AttendanceDTO> attendanceDTOS;

    private List<ScoreDTO> scoreDTOS;

    private List<ClassDTO> classDTOS;

    private List<JoinClassDTO> joinClassDTOS;

}
