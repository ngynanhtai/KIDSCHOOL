package com.example.backend.converter;

import com.example.backend.dto.SubjectDTO;
import com.example.backend.model.SubjectEntity;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.TypeSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class SubjectConverter {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TypeSubjectRepository typeSubjectRepository;

    public SubjectDTO toDTO(SubjectEntity subjectEntity){
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(subjectEntity.getId());
        subjectDTO.setName(subjectEntity.getName());
        subjectDTO.setFee(subjectEntity.getFee());
        subjectDTO.setHour(subjectEntity.getHour());
        subjectDTO.setStartDate(subjectEntity.getStartDate());
        subjectDTO.setEndDate(subjectEntity.getEndDate());
        subjectDTO.setStatus(subjectEntity.getStatus());
        if(subjectEntity.getImage() != null){
            String imageBase64 = "data:image/jpeg;base64, " +  Base64.getEncoder().encodeToString(subjectEntity.getImage());
            subjectDTO.setImage(imageBase64);
        }
        if (subjectEntity.getEmployeeEntity() != null) {
            subjectDTO.setEmployee_id(subjectEntity.getEmployeeEntity().getId());
        }
        if (subjectEntity.getTypeSubjectEntity() != null) {
            subjectDTO.setTypeSubject_id(subjectEntity.getTypeSubjectEntity().getId());
        }
        return subjectDTO;
    }

    public SubjectEntity toEntity(SubjectDTO subjectDTO){
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(subjectDTO.getId());
        subjectEntity.setName(subjectDTO.getName());
        subjectEntity.setFee(subjectDTO.getFee());
        subjectEntity.setHour(subjectDTO.getHour());
        subjectEntity.setStartDate(subjectDTO.getStartDate());
        subjectEntity.setEndDate(subjectDTO.getEndDate());
        subjectEntity.setStatus(subjectDTO.getStatus());
        if(subjectDTO.getImage() != null){
            String imageBase64 = subjectDTO.getImage().split(",")[1];
            subjectEntity.setImage(Base64.getDecoder().decode(imageBase64));
        }
        if(subjectDTO.getEmployee_id() != null) {
            subjectEntity.setEmployeeEntity(employeeRepository.findById(subjectDTO.getEmployee_id()).get());
        }
        if(subjectDTO.getTypeSubject_id() != null) {
            subjectEntity.setTypeSubjectEntity(typeSubjectRepository.findById(subjectDTO.getTypeSubject_id()).get());
        }
        return subjectEntity;
    }

    public SubjectEntity toEntity(SubjectEntity result, SubjectDTO subjectDTO){
        result.setName(subjectDTO.getName());
        result.setFee(subjectDTO.getFee());
        result.setHour(subjectDTO.getHour());
        result.setStartDate(subjectDTO.getStartDate());
        result.setEndDate(subjectDTO.getEndDate());
        result.setStatus(subjectDTO.getStatus());
        if(subjectDTO.getImage() != null){
            String imageBase64 = subjectDTO.getImage().split(",")[1];
            result.setImage(Base64.getDecoder().decode(imageBase64));
        }
        if(subjectDTO.getEmployee_id() != null) {
            result.setEmployeeEntity(employeeRepository.findById(subjectDTO.getEmployee_id()).get());
        }
        if(subjectDTO.getTypeSubject_id() != null) {
            result.setTypeSubjectEntity(typeSubjectRepository.findById(subjectDTO.getTypeSubject_id()).get());
        }
        return result;
    }
}
