package com.example.backend.convertresponse;

import com.example.backend.converter.EmployeeConverter;
import com.example.backend.dtoresponse.SalaryDTORes;
import com.example.backend.model.SalaryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class SalaryConverterRes {

    @Autowired
    private EmployeeConverterRes employeeConverterRes;

    public SalaryDTORes toDTO(SalaryEntity salaryEntity) throws UnsupportedEncodingException {
        SalaryDTORes salaryDTORes = new SalaryDTORes();
        salaryDTORes.setId(salaryEntity.getId());
        salaryDTORes.setOtHours(salaryEntity.getOtHours());
        salaryDTORes.setTax(salaryEntity.getTax());
        salaryDTORes.setNote(salaryEntity.getNote());
        salaryDTORes.setTotalIncome(salaryEntity.getTotalIncome());
        salaryDTORes.setCreateAt(salaryEntity.getCreateAt());
        salaryDTORes.setStatus(salaryEntity.getStatus());
        salaryDTORes.setMonth(salaryEntity.getMonth());
        salaryDTORes.setBonus(salaryEntity.getBonus());
        salaryDTORes.setEmployeeDTORes(salaryEntity.getEmployeeEntity() != null ? employeeConverterRes.toDTO(salaryEntity.getEmployeeEntity()) : null);
        return salaryDTORes;
    }
}
