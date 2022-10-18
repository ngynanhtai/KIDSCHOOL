package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.BlobType;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;

    private String accountId;

    private String password;

    private String firstName;

    private String lastName;

    private Boolean gender;

    private String image;

    private int age;

    private String birthday;

    private String phone;

    private String email;

    private String address;

    private String startDate;

    private String endDate;

    private String createAt;

    private Boolean status;

    private Long role_id;

    private Long school_id;

    private Long parent_id;

}
