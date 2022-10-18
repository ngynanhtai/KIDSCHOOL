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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Subject")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private Double fee;
    private Float hour;
    private String startDate;
    private String endDate;
    private Boolean status;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @ManyToOne
    private EmployeeEntity employeeEntity;

    @ManyToMany(mappedBy = "subjectEntities")
    private List<ClassEntity> classEntities;

    @OneToMany(mappedBy = "subjectEntity")
    private List<FeedbackEntity> feedbackEntities;

    @OneToMany(mappedBy = "subjectEntity")
    private List<TimeTableEntity> timeTableEntities;

    @OneToMany(mappedBy = "subjectEntity")
    private List<ScoreEntity> scoreEntities;

    @ManyToOne
    private TypeSubjectEntity typeSubjectEntity;
}
