package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.BlobType;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;

@Getter
@Setter
public class EmployeeDTO {

    private Long id;

    private String accountID;

    private String password;

    private String firstName;

    private String lastName;

    private String birthday;

    private Boolean gender;

    private String image;

    private int age;

    private String phone;

    private String email;

    private String address;

    private String degree;

    private String startDate;

    private String endDate;

    private String createAt;

    private Boolean status;

    private Long school_id;

    private Long role_id;

    private Long levelEmployee_id;
}
