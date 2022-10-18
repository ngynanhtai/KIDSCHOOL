package com.example.backend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "School")
public class SchoolEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Boolean status;

    @OneToMany(mappedBy = "schoolEntity")
    private List<ClassEntity> classEntities;

    @OneToMany(mappedBy = "schoolEntity")
    private List<StudentEntity> studentEntities;

    @OneToMany(mappedBy = "schoolEntity")
    private List<SuggestionEntity> suggestionEntities;

    @OneToMany(mappedBy = "schoolEntity")
    private List<EmployeeEntity> employeeEntities;

    @OneToMany(mappedBy = "schoolEntity")
    private List<FeedbackEntity> feedbackEntities;

    @OneToMany(mappedBy = "schoolEntity")
    private List<EventEntity> eventEntities;

    @OneToMany(mappedBy = "schoolEntity")
    private List<JobApplicationEntity> jobApplicationEntities;

    @OneToMany(mappedBy = "schoolEntity")
    private List<AnnouncementEntity> announcementEntities;

    @OneToMany(mappedBy = "schoolEntity")
    private List<JoinClassEntity> joinClassEntities;

}
