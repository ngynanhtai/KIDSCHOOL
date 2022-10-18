package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Feedback")
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    private Boolean isReply;
    @Column(columnDefinition = "TEXT")
    private String replyContent;

    private String createAt;

    @ManyToOne
    private StudentEntity studentEntity;

    @ManyToOne
    private SubjectEntity subjectEntity;

    @ManyToOne
    private EmployeeEntity employeeEntity;

    @ManyToOne
    private SchoolEntity schoolEntity;
}
