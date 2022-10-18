package com.example.backend.converter;

import com.example.backend.dto.ScoreDTO;
import com.example.backend.model.ScoreEntity;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoreConverter {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public ScoreDTO toDTO(ScoreEntity scoreEntity){
        ScoreDTO scoreDTO = new ScoreDTO();
        scoreDTO.setId(scoreEntity.getId());
        scoreDTO.setScore(scoreEntity.getScore());
        scoreDTO.setStatus(scoreEntity.getStatus());
        scoreDTO.setNote(scoreEntity.getNote());
        scoreDTO.setStudent_id(scoreEntity.getStudentEntity() != null ? scoreEntity.getStudentEntity().getId() : null);
        scoreDTO.setEmployee_id(scoreEntity.getEmployeeEntity() != null ? scoreEntity.getEmployeeEntity().getId() : null);
        scoreDTO.setSubject_id(scoreEntity.getSubjectEntity() != null ? scoreEntity.getSubjectEntity().getId() : null);
        return scoreDTO;
    }

    public ScoreEntity toEntity(ScoreDTO scoreDTO){
        ScoreEntity scoreEntity = new ScoreEntity();
        scoreEntity.setId(scoreDTO.getId() != null ? scoreDTO.getId() : 0);
        scoreEntity.setScore(scoreDTO.getScore());
        scoreEntity.setStatus(scoreDTO.getStatus() != null ? scoreDTO.getStatus() : false);
        scoreEntity.setNote(scoreDTO.getNote());
        scoreEntity.setStudentEntity(!scoreDTO.getStudent_id().toString().isEmpty() ? studentRepository.findById(scoreDTO.getStudent_id()).get() : null);
        scoreEntity.setEmployeeEntity(!scoreDTO.getEmployee_id().toString().isEmpty() ? employeeRepository.findById(scoreDTO.getEmployee_id()).get() : null);
        scoreEntity.setSubjectEntity(!scoreDTO.getSubject_id().toString().isEmpty() ? subjectRepository.findById(scoreDTO.getSubject_id()).get() : null);
        return scoreEntity;
    }

    public ScoreEntity toEntity(ScoreEntity result, ScoreDTO scoreDTO){
        result.setScore(scoreDTO.getScore());
        result.setStatus(scoreDTO.getStatus());
        result.setNote(scoreDTO.getNote());
        result.setStudentEntity(!scoreDTO.getStudent_id().toString().isEmpty() ? studentRepository.findById(scoreDTO.getStudent_id()).get() : null);
        result.setEmployeeEntity(!scoreDTO.getEmployee_id().toString().isEmpty() ? employeeRepository.findById(scoreDTO.getEmployee_id()).get() : null);
        result.setSubjectEntity(!scoreDTO.getSubject_id().toString().isEmpty() ? subjectRepository.findById(scoreDTO.getSubject_id()).get() : null);
        return result;
    }
}
