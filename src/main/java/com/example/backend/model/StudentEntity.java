package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.BlobType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String accountId;

    private String password;

    private String firstName;

    private String lastName;

    private Boolean gender;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    private int age;

    private String birthday;

    private String phone;

    private String email;

    private String address;

    private String startDate;

    private String endDate;

    private String createAt;

    private Boolean status;

    @ManyToOne
    private RoleEntity roleEntity;

    @ManyToOne
    private SchoolEntity schoolEntity;

    @OneToMany(mappedBy = "studentEntity")
    private List<FeedbackEntity> feedbackEntities;

    @OneToMany(mappedBy = "studentEntity")
    private List<HealthReportEntity> healthReportEntities;

    @OneToMany(mappedBy = "studentEntity")
    private List<AttendanceEntity> attendanceEntities;

    @OneToMany(mappedBy = "studentEntity")
    private List<ScoreEntity> scoreEntities;

    @ManyToMany(mappedBy = "studentEntities")
    private List<ClassEntity> classEntities;

    @ManyToOne
    private ParentEntity parentEntity;

    @OneToMany(mappedBy = "studentEntity")
    private List<JoinClassEntity> joinClassEntities;
}
