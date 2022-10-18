package com.example.backend.controller;

import com.example.backend.dto.TimetableDTO;
import com.example.backend.dtoresponse.TimetableDTORes;
import com.example.backend.model.TimeTableEntity;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.ITimetableService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/timetable")
public class TimetableController {
    @Autowired
    private ITimetableService timetableService;

    public HttpMessage httpMessage;
    private GenericResponse<TimetableDTORes> response;
    private GenericResponse<List<TimetableDTORes>> listResponse;

    public TimetableController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    public GenericResponse<TimetableDTORes> findOne(@PathVariable("id") Long id){
        TimetableDTORes data = timetableService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    public GenericResponse<List<TimetableDTORes>> findAll(){
        return listResponse.success(timetableService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('TEACHER') or hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<TimetableDTORes> create(@RequestBody TimetableDTO timetableDTO){
        return response.success(timetableService.create(timetableDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('TEACHER') or hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<TimetableDTORes> update(@PathVariable("id") Long id, @RequestBody TimetableDTO timetableDTO){
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(timetableDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(timetableDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(timetableService.update(timetableDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('TEACHER') or hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<TimetableDTORes> delete(@PathVariable("id") Long id){
        timetableService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }
}
