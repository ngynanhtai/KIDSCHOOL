package com.example.backend.dtoresponse;

import com.example.backend.dto.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClassDTORes {

    private Long id;

    private String classId;

    private String name;

    private Double fee;

    private String startDate;

    private String endDate;

    private Boolean status;

    private int capacity;

    private SchoolDTO schoolDTO;

    private TypeClassDTO typeClassDTO;

    private LevelClassDTO levelClassDTO;



    private List<JoinClassDTO> joinClassDTOS;

    private List<TimetableDTO> timetableDTOS;

    private List<EmployeeDTO> employeeDTOS;

    private List<AttendanceDTO> attendanceDTOS;

    private List<SubjectDTO> subjectDTOS;

    private List<StudentDTO> studentDTOS;

}
