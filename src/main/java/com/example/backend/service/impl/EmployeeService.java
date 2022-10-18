package com.example.backend.service.impl;


import com.example.backend.converter.EmployeeConverter;
import com.example.backend.convertresponse.ClassConverterRes;
import com.example.backend.convertresponse.EmployeeConverterRes;
import com.example.backend.dto.etc.AccountDTO;
import com.example.backend.dto.EmployeeDTO;
import com.example.backend.dto.etc.ChangePasswordDTO;
import com.example.backend.dtoresponse.AccountDTORes;
import com.example.backend.dtoresponse.ClassDTORes;
import com.example.backend.dtoresponse.EmployeeDTORes;
import com.example.backend.model.ClassEntity;
import com.example.backend.model.EmployeeEntity;
import com.example.backend.repository.ClassRepository;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private EmployeeConverterRes employeeConverterRes;

    @Autowired
    private ClassConverterRes classConverterRes;

    @Autowired
    private ClassRepository classRepository;

    @Override
    public EmployeeDTORes findOne(Long id) throws UnsupportedEncodingException {
        Optional<EmployeeEntity> data = employeeRepository.findById(id);
        return data.isPresent()
                ? employeeConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public EmployeeDTORes create(EmployeeDTO employeeDTO) throws UnsupportedEncodingException {
        EmployeeEntity entity = employeeConverter.toEntity(employeeDTO);
        entity.setCreateAt(new Date().toString());
        entity.setPassword(encoder.encode(employeeDTO.getPassword()));
        employeeRepository.save(entity);
        entity.setAccountID(calculateClassId(entity.getId()));
        return employeeConverterRes.toDTO(employeeRepository.saveAndFlush(entity));
    }

    private String calculateClassId(Long id){
        String DEFAULT_PREFIX = "E";
        String DEFAULT_VALUE = "";
        String idCast = id.toString();
        switch (idCast.length()){
            case 1:
                DEFAULT_VALUE = "00000" + idCast;
                break;
            case 2:
                DEFAULT_VALUE = "0000" + idCast;
                break;
            case 3:
                DEFAULT_VALUE = "000" + idCast;
                break;
            case 4:
                DEFAULT_VALUE = "00" + idCast;
                break;
            case 5:
                DEFAULT_VALUE = "0" + idCast;
                break;
            case 6:
                DEFAULT_VALUE = idCast;
                break;
            default: break;
        }
        return DEFAULT_PREFIX + DEFAULT_VALUE;
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDTORes update(EmployeeDTO employeeDTO) throws UnsupportedEncodingException {
        EmployeeEntity currEntity = employeeRepository.findById(employeeDTO.getId()).get();
        EmployeeEntity entity = employeeConverter.toEntity(currEntity, employeeDTO);
        return employeeConverterRes.toDTO(employeeRepository.save(entity));
    }

    @Override
    public List<EmployeeDTORes> findAll() throws UnsupportedEncodingException {
        List<EmployeeDTORes> result = new ArrayList<>();
        List<EmployeeEntity> entityList = employeeRepository.findAll();
        for (EmployeeEntity item : entityList) {
            result.add(employeeConverterRes.toDTO(item));
        }
        return result;
    }

    @Override
    public List<ClassDTORes> findAllClassByTeacherID(Long id) throws UnsupportedEncodingException {
        Optional<EmployeeEntity> currOpt = employeeRepository.findById(id);
        if(!currOpt.isPresent()){
            return null;
        }

        EmployeeEntity curr = currOpt.get();
        if(!curr.getRoleEntity().getId().equals(Long.parseLong("4"))){
            return null;
        }

        List<ClassEntity> entityList = curr.getClassEntities();
        if(entityList.size() == 0) return new ArrayList<>();

        List<ClassDTORes> result = new ArrayList<>();
        for (ClassEntity classEntity : entityList){
            result.add(classConverterRes.toDTO(classEntity));
        }

        return result;
    }



    @Override
    public Boolean checkAccountId(String accountId) {
        return employeeRepository.findByAccountId(accountId) != null ? true : false;
    }

    @Override
    public AccountDTORes checkLogin(AccountDTO accountDTO) {
        EmployeeEntity employee = employeeRepository.findByAccountId(accountDTO.getUsername());
        if (employee != null && employee.getPassword().equals(accountDTO.getPassword())) {
            AccountDTORes accountDTORes = new AccountDTORes();
            accountDTORes.setId(employee.getId());
            accountDTORes.setUsername(employee.getAccountID());
            accountDTORes.setPassword(employee.getPassword());
            accountDTORes.setRoleName(employee.getRoleEntity().getName());
            return accountDTORes;
        }
        return null;
    }

    @Override
    public List<ClassDTORes> addClassForTeacher(Long id, List<Long> ids) throws UnsupportedEncodingException {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        if(!employeeEntity.isPresent()){
            return null;
        }
        if (!employeeEntity.get().getRoleEntity().getId().equals(Long.parseLong("4"))){
            return null;
        }
        EmployeeEntity curr = employeeEntity.get();
        for (Long classID: ids){
            ClassEntity classEntity = classRepository.findById(classID).get();
            classEntity.getEmployeeEntities().add(curr);
            classRepository.save(classEntity);
        }
        EmployeeEntity data = employeeRepository.findById(id).get();
        List<ClassDTORes> result = new ArrayList<>();
        for (ClassEntity classEntity : data.getClassEntities()){
            result.add(classConverterRes.toDTO(classEntity));
        }
        return result;
    }

    @Override
    public List<ClassDTORes> removeClassForTeacher(Long id, List<Long> ids) throws UnsupportedEncodingException {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        if(!employeeEntity.isPresent()){
            return null;
        }
        if (!employeeEntity.get().getRoleEntity().getId().equals(Long.parseLong("4"))){
            return null;
        }
        EmployeeEntity curr = employeeEntity.get();
        for (Long classID: ids){
            ClassEntity classEntity = classRepository.findById(classID).get();
            classEntity.getEmployeeEntities().remove(curr);
            classRepository.save(classEntity);
        }
        EmployeeEntity data = employeeRepository.findById(id).get();
        List<ClassDTORes> result = new ArrayList<>();
        for (ClassEntity classEntity : data.getClassEntities()){
            result.add(classConverterRes.toDTO(classEntity));
        }
        return result;
    }

    @Override
    public Boolean changePassword(Long id, ChangePasswordDTO changePasswordDTO) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        if(!employeeEntity.isPresent()){
            return false;
        }
        EmployeeEntity curr = employeeEntity.get();
        if(encoder.matches(changePasswordDTO.getOldPassword(), curr.getPassword())) {
            curr.setPassword(encoder.encode(changePasswordDTO.getNewPassword()));
            employeeRepository.save(curr);
            return true;
        }
        return false;
    }
}
