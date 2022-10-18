package com.example.backend.model;

import lombok.*;
import org.hibernate.type.BlobType;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String accountID;

    private String password;

    private String firstName;

    private String lastName;

    private String birthday;

    private Boolean gender;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    private int age;

    private String phone;

    private String email;

    private String address;

    private String degree;

    private String startDate;

    private String endDate;

    private String createAt;

    private Boolean status;

    @ManyToOne
    private SchoolEntity schoolEntity;

    @ManyToOne
    private RoleEntity roleEntity;

    @ManyToOne
    private LevelEmployeeEntity levelEmployeeEntity; //

    @OneToMany(mappedBy = "employeeEntity")
    private List<SalaryEntity> salaryEntities;

    @OneToMany(mappedBy = "employeeEntity")
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private List<SubjectEntity> subjectEntities;

    @OneToMany(mappedBy = "employeeEntity")
    private List<SuggestionEntity> suggestionEntities;

    @OneToMany(mappedBy = "employeeEntity")
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private List<FeedbackEntity> feedbackEntities;

    @ManyToMany(mappedBy = "employeeEntities")
    private List<ClassEntity> classEntities;

    @OneToMany(mappedBy = "employeeEntity")
    private List<EventParticipateEntity> eventParticipateEntities;

    @OneToMany(mappedBy = "employeeEntity")
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private List<ScoreEntity> scoreEntities;

    @OneToMany(mappedBy = "employeeEntity")
    private List<AnnouncementEntity> announcementEntities;
}
