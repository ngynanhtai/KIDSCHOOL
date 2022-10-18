package com.example.backend.service;

import com.example.backend.dto.AttendanceDTO;
import com.example.backend.dto.etc.AttendanceSheetDTO;
import com.example.backend.dtoresponse.AttendanceDTORes;
import com.example.backend.model.AnnouncementEntity;
import com.example.backend.model.AttendanceEntity;
import com.example.backend.model.utils.GenericResponse;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IAttendanceService {
    AttendanceDTORes findOne(Long id);
// note
    List<AttendanceDTORes> create(AttendanceSheetDTO attendanceSheetDTO);

    void delete(Long id);

    AttendanceDTORes update(AttendanceDTO attendanceDTO);

    List<AttendanceDTORes> findAll();

    AttendanceSheetDTO attendanceSheet(Long classId) throws UnsupportedEncodingException;
}
