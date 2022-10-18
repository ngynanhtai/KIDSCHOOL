package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.BlobType;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    private Long id;
    private String name;
    private Double fee;
    private Float hour;
    private String startDate;
    private String endDate;
    private Boolean status;

    private String image;

    private Long employee_id;
    private Long typeSubject_id;
}
