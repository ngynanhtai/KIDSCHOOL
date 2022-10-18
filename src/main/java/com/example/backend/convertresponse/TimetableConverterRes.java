package com.example.backend.convertresponse;

import com.example.backend.converter.ClassConverter;
import com.example.backend.converter.SubjectConverter;
import com.example.backend.dtoresponse.TimetableDTORes;
import com.example.backend.model.TimeTableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimetableConverterRes {

    @Autowired
    private SubjectConverter subjectConverter;

    @Autowired
    private ClassConverter classConverter;

    public TimetableDTORes toDTO(TimeTableEntity timeTableEntity){
        TimetableDTORes timetableDTORes = new TimetableDTORes();
        timetableDTORes.setId(timeTableEntity.getId());
        timetableDTORes.setStartHour(timeTableEntity.getStartHour());
        timetableDTORes.setEndHour(timeTableEntity.getEndHour());
        timetableDTORes.setDateStudy(timeTableEntity.getDateStudy());
        timetableDTORes.setNote(timeTableEntity.getNote());
        timetableDTORes.setStatus(timeTableEntity.getStatus());
        timetableDTORes.setDayOfWeek(timeTableEntity.getDayOfWeek());
        timetableDTORes.setSubjectDTO(timeTableEntity.getSubjectEntity() != null ? subjectConverter.toDTO(timeTableEntity.getSubjectEntity()) : null);
        timetableDTORes.setClassDTO(timeTableEntity.getClassEntity() != null ? classConverter.toDTO(timeTableEntity.getClassEntity()) : null);
        return timetableDTORes;
    }
}
