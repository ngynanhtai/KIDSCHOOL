package com.example.backend.converter;

import com.example.backend.dto.FeedbackDTO;
import com.example.backend.model.FeedbackEntity;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.SchoolRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackConverter {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    public FeedbackDTO toDTO(FeedbackEntity entity) {
        FeedbackDTO result = new FeedbackDTO();
        result.setId(entity.getId());
        result.setCreateAt(entity.getCreateAt());
        result.setIsReply(entity.getIsReply());
        result.setContent(entity.getContent());
        result.setTitle(entity.getTitle());
        result.setReplyContent(entity.getReplyContent());
        if (entity.getStudentEntity() != null) {
            result.setStudent_id(entity.getStudentEntity().getId());
        }
        if (entity.getSubjectEntity() != null) {
            result.setSubject_id(entity.getSubjectEntity().getId());
        }
        if (entity.getEmployeeEntity() != null) {
            result.setEmp_id(entity.getEmployeeEntity().getId());
        }
        if (entity.getSchoolEntity() != null) {
            result.setSchool_id(entity.getSchoolEntity().getId());
        }
        return result;
    }

    public FeedbackEntity toEntity(FeedbackDTO dto) {
        FeedbackEntity result = new FeedbackEntity();
        result.setId(dto.getId());
        result.setCreateAt(dto.getCreateAt());
        result.setIsReply(dto.getIsReply());
        result.setContent(dto.getContent());
        result.setTitle(dto.getTitle());
        result.setReplyContent(dto.getReplyContent());
        if (dto.getStudent_id() != null) {
            result.setStudentEntity(studentRepository.findById(dto.getStudent_id()).get());
        }
        if (dto.getSubject_id() != null) {
            result.setSubjectEntity(subjectRepository.findById(dto.getSubject_id()).get());
        }
        if (dto.getEmp_id() != null) {
            result.setEmployeeEntity(employeeRepository.findById(dto.getEmp_id()).get());
        }
        if (dto.getSchool_id() != null) {
            result.setSchoolEntity(schoolRepository.findById(dto.getSchool_id()).get());
        }
        return result;
    }

    public FeedbackEntity toEntity(FeedbackEntity result, FeedbackDTO dto) {
        result.setCreateAt(dto.getCreateAt());
        result.setIsReply(dto.getIsReply());
        result.setContent(dto.getContent());
        result.setTitle(dto.getTitle());
        result.setReplyContent(dto.getReplyContent());
        if (dto.getStudent_id() != null) {
            result.setStudentEntity(studentRepository.findById(dto.getStudent_id()).get());
        }
        if (dto.getSubject_id() != null) {
            result.setSubjectEntity(subjectRepository.findById(dto.getSubject_id()).get());
        }
        if (dto.getEmp_id() != null) {
            result.setEmployeeEntity(employeeRepository.findById(dto.getEmp_id()).get());
        }
        if (dto.getSchool_id() != null) {
            result.setSchoolEntity(schoolRepository.findById(dto.getSchool_id()).get());
        }
        return result;
    }
}
