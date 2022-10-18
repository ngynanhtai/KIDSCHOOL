package com.example.backend.converter;

import com.example.backend.dto.LevelEmployeeDTO;
import com.example.backend.model.LevelEmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class LevelEmployeeConverter {

    public LevelEmployeeDTO toDTO(LevelEmployeeEntity entity) {
        LevelEmployeeDTO result = new LevelEmployeeDTO();
        result.setId(entity.getId());
        result.setAllowance(entity.getAllowance());
        result.setExp(entity.getExp());
        result.setMonthSalary(entity.getMonthSalary());
        result.setOtIncome(entity.getOtIncome());
        return result;
    }

    public LevelEmployeeEntity toEntity(LevelEmployeeDTO dto) {
        LevelEmployeeEntity result = new LevelEmployeeEntity();
        result.setId(dto.getId());
        result.setAllowance(dto.getAllowance());
        result.setExp(dto.getExp());
        result.setMonthSalary(dto.getMonthSalary());
        result.setOtIncome(dto.getOtIncome());
        return result;
    }

    public LevelEmployeeEntity toEntity(LevelEmployeeEntity result, LevelEmployeeDTO dto) {
        result.setAllowance(dto.getAllowance());
        result.setExp(dto.getExp());
        result.setMonthSalary(dto.getMonthSalary());
        result.setOtIncome(dto.getOtIncome());
        return result;
    }
}
