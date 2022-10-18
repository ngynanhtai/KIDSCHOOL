package com.example.backend.model;
import lombok.*;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "roleEntity")
    private List<EmployeeEntity> employeeEntities;

    @OneToMany(mappedBy =  "roleEntity")
    private List<StudentEntity> studentEntities;

    @OneToMany(mappedBy = "roleEntity")
    private List<JobApplicationEntity> jobApplicationEntities;

    @ManyToMany(mappedBy = "roleEntities")
    private List<AnnouncementEntity> announcementEntities;
}
