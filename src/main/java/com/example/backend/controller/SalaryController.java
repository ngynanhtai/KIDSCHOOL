package com.example.backend.controller;

import com.example.backend.dto.SalaryDTO;
import com.example.backend.dtoresponse.SalaryDTORes;
import com.example.backend.model.SalaryEntity;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.ISalaryService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/salary")
public class SalaryController {
    @Autowired
    private ISalaryService salaryService;

    public HttpMessage httpMessage;
    private GenericResponse<SalaryDTORes> response;
    private GenericResponse<List<SalaryDTORes>> listResponse;

    public SalaryController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SalaryDTORes> findOne(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        SalaryDTORes data = salaryService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('HR') or hasAuthority('PRINCIPAL')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<SalaryDTORes>> findAll() throws UnsupportedEncodingException {
        return listResponse.success(salaryService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SalaryDTORes> create(@RequestBody SalaryDTO salaryDTO) throws UnsupportedEncodingException {
        return response.success(salaryService.create(salaryDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/list/")
    @PreAuthorize("hasAuthority('HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<SalaryDTORes>> updsateListSalary(@RequestBody List<SalaryDTO> salaryDTOList) throws UnsupportedEncodingException {
        return listResponse.success(salaryService.updateListSalary(salaryDTOList), httpMessage.UPDATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SalaryDTORes> update(@PathVariable("id") Long id, @RequestBody SalaryDTO salaryDTO) throws UnsupportedEncodingException {
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(salaryDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(salaryDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(salaryService.update(salaryDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SalaryDTORes> delete(@PathVariable("id") Long id){
        salaryService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }

    @GetMapping("/month/{id}")
    @PreAuthorize("hasAuthority('HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<SalaryDTORes>> getAllSalariesByMonth(@PathVariable("id") int month) throws UnsupportedEncodingException {
        if(month <= 0 || month > 12){
            return new GenericResponse<List<SalaryDTORes>>().error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
        }
        List<SalaryDTORes> result = salaryService.getAllSalariesByMonth(month);
        return new GenericResponse<List<SalaryDTORes>>().success(result, httpMessage.GET_SUCCESS);
    }
}
