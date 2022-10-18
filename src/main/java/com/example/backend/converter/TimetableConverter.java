package com.example.backend.converter;

import com.example.backend.dto.TimetableDTO;
import com.example.backend.model.TimeTableEntity;
import com.example.backend.repository.ClassRepository;
import com.example.backend.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimetableConverter {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ClassRepository classRepository;

    public TimetableDTO toDTO(TimeTableEntity timeTableEntity){
        TimetableDTO timetableDTO = new TimetableDTO();
        timetableDTO.setId(timeTableEntity.getId());
        timetableDTO.setStartHour(timeTableEntity.getStartHour());
        timetableDTO.setEndHour(timeTableEntity.getEndHour());
        timetableDTO.setDateStudy(timeTableEntity.getDateStudy());
        timetableDTO.setNote(timeTableEntity.getNote());
        timetableDTO.setStatus(timeTableEntity.getStatus());
        timetableDTO.setDayOfWeek(timeTableEntity.getDayOfWeek());
        timetableDTO.setClass_id(timeTableEntity.getClassEntity() != null ? timeTableEntity.getClassEntity().getId() : null);
        timetableDTO.setSubject_id(timeTableEntity.getSubjectEntity() != null ? timeTableEntity.getSubjectEntity().getId() : null);
        return timetableDTO;
    }

    public TimeTableEntity toEntity(TimetableDTO timetableDTO){
        TimeTableEntity timeTableEntity = new TimeTableEntity();
        timeTableEntity.setId(timetableDTO.getId());
        timeTableEntity.setStartHour(timetableDTO.getStartHour());
        timeTableEntity.setEndHour(timetableDTO.getEndHour());
        timeTableEntity.setDateStudy(timetableDTO.getDateStudy());
        timeTableEntity.setNote(timetableDTO.getNote());
        timeTableEntity.setStatus(timetableDTO.getStatus());
        timeTableEntity.setDayOfWeek(timetableDTO.getDayOfWeek());
        timeTableEntity.setClassEntity(!timetableDTO.getClass_id().toString().isEmpty() ? classRepository.findById(timetableDTO.getClass_id()).get() : null);
        timeTableEntity.setSubjectEntity(!timetableDTO.getSubject_id().toString().isEmpty() ? subjectRepository.findById(timetableDTO.getSubject_id()).get() : null);
        return timeTableEntity;
    }

    public TimeTableEntity toEntity(TimeTableEntity result, TimetableDTO timetableDTO){
        result.setStartHour(timetableDTO.getStartHour());
        result.setEndHour(timetableDTO.getEndHour());
        result.setDateStudy(timetableDTO.getDateStudy());
        result.setNote(timetableDTO.getNote());
        result.setStatus(timetableDTO.getStatus());
        result.setDayOfWeek(timetableDTO.getDayOfWeek());
        result.setClassEntity(!timetableDTO.getClass_id().toString().isEmpty() ? classRepository.findById(timetableDTO.getClass_id()).get() : null);
        result.setSubjectEntity(!timetableDTO.getSubject_id().toString().isEmpty() ? subjectRepository.findById(timetableDTO.getSubject_id()).get() : null);
        return result;
    }
}
