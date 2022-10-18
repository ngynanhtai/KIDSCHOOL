package com.example.backend.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "JobApplication")
public class JobApplicationEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;
    @Column(columnDefinition = "TEXT")
    private String cv;

    private Boolean isReply;

    private Boolean isInterview;

    private Boolean isApprove;

    private Boolean status;

    @ManyToOne
    private RoleEntity roleEntity;

    @ManyToOne
    private SchoolEntity schoolEntity;
}
