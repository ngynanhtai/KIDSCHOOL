package com.example.backend.controller;


import com.example.backend.dto.AttendanceDTO;
import com.example.backend.dto.etc.AttendanceSheetDTO;
import com.example.backend.dto.etc.ClassMemberDTO;
import com.example.backend.dtoresponse.AttendanceDTORes;
import com.example.backend.dtoresponse.StudentDTORes;
import com.example.backend.model.AttendanceEntity;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.IAttendanceService;
import com.example.backend.service.IClassService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private IAttendanceService attendanceService;

    public HttpMessage httpMessage;
    private GenericResponse<AttendanceDTORes> response;
    private GenericResponse<List<AttendanceDTORes>> listResponse;

    public AttendanceController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('TEACHER','PRINCIPAL','ACADEMIC','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<AttendanceDTORes> findOne(@PathVariable("id") Long id){
        AttendanceDTORes data = attendanceService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('TEACHER','PRINCIPAL','ACADEMIC','ADMIN','USER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<AttendanceDTORes>> findAll(){
        return listResponse.success(attendanceService.findAll(), httpMessage.GET_SUCCESS);
    }

    @GetMapping("/{classId}/sheet")
    @PreAuthorize("hasAuthority('TEACHER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<AttendanceSheetDTO> attendanceSheet(@PathVariable("classId") Long classId) throws UnsupportedEncodingException {
        if(classId.toString().isEmpty()){
            return new GenericResponse<AttendanceSheetDTO>().error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<AttendanceSheetDTO>().success(attendanceService.attendanceSheet(classId), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('TEACHER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<AttendanceDTORes>> create(@RequestBody AttendanceSheetDTO attendanceSheetDTO){
        return listResponse.success(attendanceService.create(attendanceSheetDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<AttendanceDTORes> update(@PathVariable("id") Long id, @RequestBody AttendanceDTO attendanceDTO){
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(attendanceDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(attendanceDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(attendanceService.update(attendanceDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<AttendanceDTORes> delete(@PathVariable("id") Long id){
        attendanceService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }
}
