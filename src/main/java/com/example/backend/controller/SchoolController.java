package com.example.backend.controller;

import com.example.backend.dto.SchoolDTO;
import com.example.backend.dtoresponse.SchoolDTORes;
import com.example.backend.model.SchoolEntity;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.ISchoolService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/school")
public class SchoolController {
    @Autowired
    private ISchoolService schoolService;

    public HttpMessage httpMessage;
    private GenericResponse<SchoolDTORes> response;
    private GenericResponse<List<SchoolDTORes>> listResponse;

    public SchoolController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SchoolDTORes> findOne(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        SchoolDTORes data = schoolService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    //@PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR','ACADEMIC')")
    //@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<SchoolDTORes>> findAll() throws UnsupportedEncodingException {
        return listResponse.success(schoolService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SchoolDTORes> create(@RequestBody SchoolDTO schoolDTO) throws UnsupportedEncodingException {
        return response.success(schoolService.create(schoolDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SchoolDTORes> update(@PathVariable("id") Long id, @RequestBody SchoolDTO schoolDTO) throws UnsupportedEncodingException {
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(schoolDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(schoolDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(schoolService.update(schoolDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SchoolDTORes> delete(@PathVariable("id") Long id){
        schoolService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }
}
