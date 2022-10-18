package com.example.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParentDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private Boolean gender;

    private String phone;

    private String birthday;

    private String address;

    private String email;
}
