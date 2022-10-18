package com.example.backend.service.impl;

import com.example.backend.converter.TimetableConverter;
import com.example.backend.convertresponse.TimetableConverterRes;
import com.example.backend.dto.TimetableDTO;
import com.example.backend.dtoresponse.TimetableDTORes;
import com.example.backend.model.TimeTableEntity;
import com.example.backend.repository.TimetableRepository;
import com.example.backend.service.ITimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimetableService implements ITimetableService {

    @Autowired
    private TimetableRepository timetableRepository;

    @Autowired
    private TimetableConverter timetableConverter;

    @Autowired
    private TimetableConverterRes timetableConverterRes;

    @Override
    public TimetableDTORes findOne(Long id) {
        Optional<TimeTableEntity> data = timetableRepository.findById(id);
        return data.isPresent()
                ? timetableConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public TimetableDTORes create(TimetableDTO timetableDTO) {
        TimeTableEntity timeTableEntity = timetableConverter.toEntity(timetableDTO);
        timeTableEntity.setStatus(false);
        return timetableConverterRes.toDTO(timetableRepository.save(timeTableEntity));
    }

    @Override
    public void delete(Long id) {
        timetableRepository.deleteById(id);
    }

    @Override
    public TimetableDTORes update(TimetableDTO timetableDTO) {
        TimeTableEntity curr = timetableRepository.findById(timetableDTO.getId()).get();
        TimeTableEntity entity = timetableConverter.toEntity(curr, timetableDTO);
        return timetableConverterRes.toDTO(timetableRepository.save(entity));
    }

    @Override
    public List<TimetableDTORes> findAll() {
        List<TimetableDTORes> result = new ArrayList<>();
        List<TimeTableEntity> entityList = timetableRepository.findAll();
        for (TimeTableEntity timeTableEntity : entityList){
            result.add(timetableConverterRes.toDTO(timeTableEntity));
        }
        return result;
    }
}
