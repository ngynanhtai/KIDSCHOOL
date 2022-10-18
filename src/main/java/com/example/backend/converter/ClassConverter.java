package com.example.backend.converter;

import com.example.backend.dto.ClassDTO;
import com.example.backend.model.ClassEntity;
import com.example.backend.repository.LevelClassRepository;
import com.example.backend.repository.SchoolRepository;
import com.example.backend.repository.TypeClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassConverter {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private TypeClassRepository typeClassRepository;

    @Autowired
    private LevelClassRepository levelClassRepository;


    public ClassDTO toDTO(ClassEntity entity) {
        ClassDTO result = new ClassDTO();
        result.setId(entity.getId());
        result.setClassId(entity.getClassId());
        result.setName(entity.getName());
        result.setFee(entity.getFee());
        result.setStartDate(entity.getStartDate());
        result.setEndDate(entity.getEndDate());
        result.setStatus(entity.getStatus());
        result.setCapacity(entity.getCapacity());
        if (entity.getSchoolEntity() != null) {
            result.setSchool_id(entity.getSchoolEntity().getId());
        }
        if (entity.getTypeClassEntity() != null) {
            result.setTypeClass_id(entity.getTypeClassEntity().getId());
        }
        if (entity.getLevelClassEntity() != null) {
            result.setLevelClass_id(entity.getLevelClassEntity().getId());
        }
        return result;
    }

    public ClassEntity toEntity(ClassDTO dto) {
        ClassEntity result = new ClassEntity();
        if(dto.getId() != null){
            result.setId(dto.getId());
        }
        if(dto.getClassId() != null){
            result.setClassId(dto.getClassId());
        }
        result.setName(dto.getName());
        result.setFee(dto.getFee());
        result.setStartDate(dto.getStartDate());
        result.setEndDate(dto.getEndDate());
        result.setCapacity(dto.getCapacity());
        result.setStatus(dto.getStatus());
        if (dto.getSchool_id() != null) {
            result.setSchoolEntity(schoolRepository.findById(dto.getSchool_id()).get());
        }
        if (dto.getTypeClass_id() != null) {
            result.setTypeClassEntity(typeClassRepository.findById(dto.getTypeClass_id()).get());
        }
        if (dto.getLevelClass_id() != null) {
            result.setLevelClassEntity(levelClassRepository.findById(dto.getLevelClass_id()).get());
        }
        return result;
    }

    public ClassEntity toEntity(ClassEntity result, ClassDTO dto) {
        result.setClassId(dto.getClassId());
        result.setName(dto.getName());
        result.setFee(dto.getFee());
        result.setStartDate(dto.getStartDate());
        result.setEndDate(dto.getEndDate());
        result.setStatus(dto.getStatus());
        result.setCapacity(dto.getCapacity());
        if (dto.getSchool_id() != null) {
            result.setSchoolEntity(schoolRepository.findById(dto.getSchool_id()).get());
        }
        if (dto.getTypeClass_id() != null) {
            result.setTypeClassEntity(typeClassRepository.findById(dto.getTypeClass_id()).get());
        }
        if (dto.getLevelClass_id() != null) {
            result.setLevelClassEntity(levelClassRepository.findById(dto.getLevelClass_id()).get());
        }
        return result;
    }
}
