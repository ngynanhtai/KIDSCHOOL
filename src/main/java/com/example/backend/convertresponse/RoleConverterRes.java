package com.example.backend.convertresponse;


import com.example.backend.converter.AnnouncementConverter;
import com.example.backend.converter.EmployeeConverter;
import com.example.backend.converter.JobApplicationConverter;
import com.example.backend.converter.StudentConverter;
import com.example.backend.dto.AnnouncementDTO;
import com.example.backend.dto.EmployeeDTO;
import com.example.backend.dto.JobApplicationDTO;
import com.example.backend.dto.StudentDTO;
import com.example.backend.dtoresponse.RoleDTORes;
import com.example.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoleConverterRes {

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private StudentConverter studentConverter;

    @Autowired
    private JobApplicationConverter jobApplicationConverter;

    @Autowired
    private AnnouncementConverter announcementConverter;

    public RoleDTORes toDTO(RoleEntity roleEntity) throws UnsupportedEncodingException {
        RoleDTORes roleDTORes = new RoleDTORes();
        roleDTORes.setId(roleEntity.getId());
        roleDTORes.setName(roleEntity.getName());

        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        List<StudentDTO> studentDTOS = new ArrayList<>();

        List<JobApplicationDTO> jobApplicationDTOS = new ArrayList<>();

        List<AnnouncementDTO> announcementDTOS = new ArrayList<>();

        if(roleEntity.getEmployeeEntities() != null && roleEntity.getEmployeeEntities().size() > 0){
            for (EmployeeEntity employeeEntity : roleEntity.getEmployeeEntities()){
                employeeDTOS.add(employeeConverter.toDTO(employeeEntity));
            }
        }
        if(roleEntity.getJobApplicationEntities() != null && roleEntity.getJobApplicationEntities().size() > 0){
            for (JobApplicationEntity jobApplicationEntity : roleEntity.getJobApplicationEntities()){
                jobApplicationDTOS.add(jobApplicationConverter.toDTO(jobApplicationEntity));
            }
        }

        if(roleEntity.getAnnouncementEntities() != null && roleEntity.getAnnouncementEntities().size() > 0){
            for (AnnouncementEntity announcementEntity : roleEntity.getAnnouncementEntities()){
                announcementDTOS.add(announcementConverter.toDTO(announcementEntity));
            }
        }


        if (roleEntity.getStudentEntities() != null && roleEntity.getStudentEntities().size() > 0){
            for (StudentEntity studentEntity : roleEntity.getStudentEntities()){
                studentDTOS.add(studentConverter.toDTO(studentEntity));
            }
        }

        roleDTORes.setEmployeeDTOS(employeeDTOS);
        roleDTORes.setStudentDTOS(studentDTOS);
        roleDTORes.setJobApplicationDTOS(jobApplicationDTOS);
        roleDTORes.setAnnouncementDTOS(announcementDTOS);
        return roleDTORes;
    }
}
