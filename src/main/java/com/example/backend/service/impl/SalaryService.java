package com.example.backend.service.impl;

import com.example.backend.converter.SalaryConverter;
import com.example.backend.convertresponse.SalaryConverterRes;
import com.example.backend.dto.SalaryDTO;
import com.example.backend.dtoresponse.SalaryDTORes;
import com.example.backend.model.SalaryEntity;
import com.example.backend.repository.SalaryRepository;
import com.example.backend.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SalaryService implements ISalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private SalaryConverter salaryConverter;

    @Autowired
    private SalaryConverterRes salaryConverterRes;

    @Override
    public SalaryDTORes findOne(Long id) throws UnsupportedEncodingException {
        Optional<SalaryEntity> data = salaryRepository.findById(id);
        return data.isPresent()
                ? salaryConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public SalaryDTORes create(SalaryDTO salaryDTO) throws UnsupportedEncodingException {
        SalaryEntity entity = salaryConverter.toEntity(salaryDTO);
        entity.setCreateAt(new Date().toString());
        return salaryConverterRes.toDTO(salaryRepository.save(entity));
    }

    @Override
    public List<SalaryDTORes> updateListSalary(List<SalaryDTO> salaryDTOList) throws UnsupportedEncodingException {
        List<SalaryDTORes> result = new ArrayList<>();
        for (SalaryDTO item : salaryDTOList) {
            item.setCreateAt(new Date().toString());
            result.add(salaryConverterRes.toDTO(salaryRepository.save(salaryConverter.toEntity(item))));
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        salaryRepository.deleteById(id);
    }

    @Override
    public SalaryDTORes update(SalaryDTO salaryDTO) throws UnsupportedEncodingException {
        SalaryEntity curr = salaryRepository.findById(salaryDTO.getId()).get();
        SalaryEntity entity = salaryConverter.toEntity(curr, salaryDTO);
        return salaryConverterRes.toDTO(salaryRepository.save(entity));
    }

    @Override
    public List<SalaryDTORes> findAll() throws UnsupportedEncodingException {
        List<SalaryDTORes> result = new ArrayList<>();
        List<SalaryEntity> entityList = salaryRepository.findAll();
        for (SalaryEntity salaryEntity: entityList){
            result.add(salaryConverterRes.toDTO(salaryEntity));
        }
        return result;
    }

    @Override
    public List<SalaryDTORes> getAllSalariesByMonth(int month) throws UnsupportedEncodingException {
        List<SalaryEntity> salaryEntities = salaryRepository.findAllSalariesByMonth(month);
        if(salaryEntities.size() == 0){
            return new ArrayList<>();
        }
        List<SalaryDTORes> result = new ArrayList<>();
        for (SalaryEntity salaryEntity : salaryEntities){
            result.add(salaryConverterRes.toDTO(salaryEntity));
        }
        return result;
    }
}
