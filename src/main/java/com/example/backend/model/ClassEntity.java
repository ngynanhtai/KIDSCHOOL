package com.example.backend.model;

import com.example.backend.model.customGenerator.StringPrefixedSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Class")
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String classId;
    private String name;
    private Double fee;
    private String startDate;
    private String endDate;
    private Boolean status;
    private int capacity;
    @ManyToOne
    private SchoolEntity schoolEntity;

    @ManyToMany
    @JoinTable(name = "class_subject", joinColumns = @JoinColumn(name = "class_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<SubjectEntity> subjectEntities;

    @ManyToMany
    @JoinTable(name = "class_student", joinColumns = @JoinColumn(name = "class_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<StudentEntity> studentEntities;


    @OneToMany(mappedBy = "classEntity")
    private List<AttendanceEntity> attendanceEntities;

    @ManyToMany
    @JoinTable(name = "class_employee", joinColumns = @JoinColumn(name = "class_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<EmployeeEntity> employeeEntities;

    @OneToMany(mappedBy = "classEntity")
    private List<TimeTableEntity> timeTableEntities;

    @ManyToOne
    private TypeClassEntity typeClassEntity;

    @ManyToOne
    private LevelClassEntity levelClassEntity;

    @OneToMany(mappedBy = "classEntity")
    private List<JoinClassEntity> joinClassEntities;
}
