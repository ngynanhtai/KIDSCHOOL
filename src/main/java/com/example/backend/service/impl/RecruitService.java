package com.example.backend.service.impl;

import com.example.backend.converter.RecruitConverter;
import com.example.backend.convertresponse.RecruitConverterRes;
import com.example.backend.dto.RecruitDTO;
import com.example.backend.dtoresponse.RecruitDTORes;
import com.example.backend.model.RecruitEntity;
import com.example.backend.repository.RecruitRepository;
import com.example.backend.service.IRecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecruitService implements IRecruitService {

    @Autowired
    private RecruitRepository recruitRepository;

    @Autowired
    private RecruitConverter recruitConverter;

    @Autowired
    private RecruitConverterRes recruitConverterRes;

    @Override
    public RecruitDTORes findOne(Long id) {
        Optional<RecruitEntity> data = recruitRepository.findById(id);
        return data.isPresent()
                ? recruitConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public RecruitDTORes create(RecruitDTO recruitDTO) {
        return recruitConverterRes.toDTO(recruitRepository.save(recruitConverter.toEntity(recruitDTO)));
    }

    @Override
    public void delete(Long id) {
        recruitRepository.deleteById(id);
    }

    @Override
    public RecruitDTORes update(RecruitDTO recruitDTO) {
        RecruitEntity curr = recruitRepository.findById(recruitDTO.getId()).get();
        RecruitEntity entity = recruitConverter.toEntity(curr, recruitDTO);
        return recruitConverterRes.toDTO(recruitRepository.save(entity));
    }

    @Override
    public List<RecruitDTORes> findAll() {
        List<RecruitDTORes> result = new ArrayList<>();
        List<RecruitEntity> entityList = recruitRepository.findAll();
        for(RecruitEntity recruitEntity : entityList){
            result.add(recruitConverterRes.toDTO(recruitEntity));
        }
        return result;
    }
}
