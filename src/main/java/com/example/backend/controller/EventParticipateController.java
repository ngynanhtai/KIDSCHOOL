package com.example.backend.controller;

import com.example.backend.dto.EventParticipateDTO;
import com.example.backend.dtoresponse.EventParticipateDTORes;
import com.example.backend.model.EmployeeEntity;
import com.example.backend.model.EventParticipateEntity;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.IEmployeeService;
import com.example.backend.service.IEventParticipateService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/eventParticipate")
public class EventParticipateController {

    @Autowired
    private IEventParticipateService eventParticipateService;

    public HttpMessage httpMessage;
    private GenericResponse<EventParticipateDTORes> response;
    private GenericResponse<List<EventParticipateDTORes>> listResponse;

    public EventParticipateController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('ACADEMIC','PRINCIPAL','ADMIN')")
//    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<EventParticipateDTORes> findOne(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        EventParticipateDTORes data = eventParticipateService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
//    @PreAuthorize("hasAnyAuthority('ACADEMIC','PRINCIPAL','ADMIN')")
//    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<EventParticipateDTORes>> findAll() throws UnsupportedEncodingException {
        return listResponse.success(eventParticipateService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
//    @PreAuthorize("hasAuthority('ACADEMIC')")
//    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<EventParticipateDTORes> create(@RequestBody EventParticipateDTO eventParticipateDTO) throws UnsupportedEncodingException {
        return response.success(eventParticipateService.create(eventParticipateDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<EventParticipateDTORes> update(@PathVariable("id") Long id, @RequestBody EventParticipateDTO eventParticipateDTO) throws UnsupportedEncodingException {
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(eventParticipateDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(eventParticipateDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(eventParticipateService.update(eventParticipateDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACADEMIC','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<EventParticipateDTORes> delete(@PathVariable("id") Long id){
        eventParticipateService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }
}
