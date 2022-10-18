package com.example.backend.converter;

import com.example.backend.dto.SalaryDTO;
import com.example.backend.model.SalaryEntity;
import com.example.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalaryConverter {

    @Autowired
    private EmployeeRepository employeeRepository;

    public SalaryDTO toDTO(SalaryEntity salaryEntity){
        SalaryDTO salaryDTO = new SalaryDTO();
        salaryDTO.setId(salaryEntity.getId());
        salaryDTO.setTax(salaryEntity.getTax());
        salaryDTO.setOtHours(salaryEntity.getOtHours());
        salaryDTO.setNote(salaryEntity.getNote());
        salaryDTO.setStatus(salaryEntity.getStatus());
        salaryDTO.setCreateAt(salaryEntity.getCreateAt());
        salaryDTO.setTotalIncome(salaryEntity.getTotalIncome());
        salaryDTO.setBonus(salaryEntity.getBonus());
        salaryDTO.setMonth(salaryEntity.getMonth());
        salaryDTO.setEmployee_id(salaryEntity.getEmployeeEntity() != null ? salaryEntity.getEmployeeEntity().getId() : null);
        return salaryDTO;
    }

    public SalaryEntity toEntity(SalaryDTO salaryDTO){
        SalaryEntity salaryEntity = new SalaryEntity();
        salaryEntity.setId(salaryDTO.getId());
        salaryEntity.setTax(salaryDTO.getTax());
        salaryEntity.setOtHours(salaryDTO.getOtHours());
        salaryEntity.setNote(salaryDTO.getNote());
        salaryEntity.setStatus(salaryDTO.getStatus());
        salaryEntity.setCreateAt(salaryDTO.getCreateAt());
        salaryEntity.setTotalIncome(salaryDTO.getTotalIncome());
        salaryEntity.setBonus(salaryDTO.getBonus());
        salaryEntity.setMonth(salaryDTO.getMonth());
        salaryEntity.setEmployeeEntity(!salaryDTO.getEmployee_id().toString().isEmpty() ? employeeRepository.findById(salaryDTO.getEmployee_id()).get() : null);
        return salaryEntity;
    }

    public SalaryEntity toEntity(SalaryEntity result, SalaryDTO salaryDTO){
        result.setTax(salaryDTO.getTax());
        result.setOtHours(salaryDTO.getOtHours());
        result.setNote(salaryDTO.getNote());
        result.setStatus(salaryDTO.getStatus());
        result.setCreateAt(salaryDTO.getCreateAt());
        result.setTotalIncome(salaryDTO.getTotalIncome());
        result.setBonus(salaryDTO.getBonus());
        result.setMonth(salaryDTO.getMonth());
        result.setEmployeeEntity(!salaryDTO.getEmployee_id().toString().isEmpty() ? employeeRepository.findById(salaryDTO.getEmployee_id()).get() : null);
        return result;
    }
}
