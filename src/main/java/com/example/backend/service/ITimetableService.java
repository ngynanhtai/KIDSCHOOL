package com.example.backend.service;

import com.example.backend.dto.TimetableDTO;
import com.example.backend.dtoresponse.TimetableDTORes;
import com.example.backend.model.TimeTableEntity;

import java.util.List;

public interface ITimetableService {
    TimetableDTORes findOne(Long id);

    TimetableDTORes create(TimetableDTO timetableDTO);

    void delete(Long id);

    TimetableDTORes update(TimetableDTO timetableDTO);

    List<TimetableDTORes> findAll();
}
