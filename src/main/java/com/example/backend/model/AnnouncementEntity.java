package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Announcement")
public class AnnouncementEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    private String createAt;

    @ManyToMany
    @JoinTable(name = "announcement_role", joinColumns = @JoinColumn(name = "announcement_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roleEntities;

    @ManyToOne
    private EmployeeEntity employeeEntity;

    @ManyToOne
    private SchoolEntity schoolEntity;

}
