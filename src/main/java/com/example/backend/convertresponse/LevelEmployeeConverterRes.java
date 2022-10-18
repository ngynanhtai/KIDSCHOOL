package com.example.backend.convertresponse;


import com.example.backend.converter.EmployeeConverter;
import com.example.backend.dto.EmployeeDTO;
import com.example.backend.dtoresponse.LevelEmployeeDTORes;
import com.example.backend.model.EmployeeEntity;
import com.example.backend.model.LevelEmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LevelEmployeeConverterRes {

    @Autowired
    private EmployeeConverter employeeConverter;

    public LevelEmployeeDTORes toDTO(LevelEmployeeEntity levelEmployeeEntity) throws UnsupportedEncodingException {
        LevelEmployeeDTORes levelEmployeeDTORes = new LevelEmployeeDTORes();
        levelEmployeeDTORes.setId(levelEmployeeEntity.getId());
        levelEmployeeDTORes.setExp(levelEmployeeEntity.getExp());
        levelEmployeeDTORes.setOtIncome(levelEmployeeEntity.getOtIncome());
        levelEmployeeDTORes.setAllowance(levelEmployeeEntity.getAllowance());
        levelEmployeeDTORes.setMonthSalary(levelEmployeeEntity.getMonthSalary());

        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        if(levelEmployeeEntity.getEmployeeEntities() != null && levelEmployeeEntity.getEmployeeEntities().size() > 0){
            for (EmployeeEntity employeeEntity : levelEmployeeEntity.getEmployeeEntities()){
                employeeDTOS.add(employeeConverter.toDTO(employeeEntity));
            }
        }
        levelEmployeeDTORes.setEmployeeDTOS(employeeDTOS);
        return levelEmployeeDTORes;
    }
}
