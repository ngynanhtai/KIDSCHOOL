package com.example.backend.dtoresponse;


import com.example.backend.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParentDTORes {
    private Long id;

    private String firstName;

    private String lastName;

    private Boolean gender;

    private String phone;

    private String birthday;

    private String address;

    private String email;

    private List<StudentDTO> studentDTOS;
}
