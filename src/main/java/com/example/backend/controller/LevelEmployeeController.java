package com.example.backend.controller;

import com.example.backend.dto.LevelEmployeeDTO;
import com.example.backend.dtoresponse.LevelEmployeeDTORes;
import com.example.backend.model.LevelEmployeeEntity;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.ILevelEmployeeService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/levelEmployee")
public class LevelEmployeeController {
    @Autowired
    private ILevelEmployeeService levelEmployeeService;

    public HttpMessage httpMessage;
    private GenericResponse<LevelEmployeeDTORes> response;
    private GenericResponse<List<LevelEmployeeDTORes>> listResponse;

    public LevelEmployeeController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('HR','PRINCIPAL','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<LevelEmployeeDTORes> findOne(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        LevelEmployeeDTORes data = levelEmployeeService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('HR','PRINCIPAL','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<LevelEmployeeDTORes>> findAll() throws UnsupportedEncodingException {
        return listResponse.success(levelEmployeeService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<LevelEmployeeDTORes> create(@RequestBody LevelEmployeeDTO levelEmployeeDTO) throws UnsupportedEncodingException {
        return response.success(levelEmployeeService.create(levelEmployeeDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','HR','PRINCIPAL')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<LevelEmployeeDTORes> update(@PathVariable("id") Long id, @RequestBody LevelEmployeeDTO levelEmployeeDTO) throws UnsupportedEncodingException {
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(levelEmployeeDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(levelEmployeeDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(levelEmployeeService.update(levelEmployeeDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<LevelEmployeeDTORes> delete(@PathVariable("id") Long id){
        levelEmployeeService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }
}
