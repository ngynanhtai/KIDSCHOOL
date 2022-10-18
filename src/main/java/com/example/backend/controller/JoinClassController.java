package com.example.backend.controller;

import com.example.backend.dto.JoinClassDTO;
import com.example.backend.dtoresponse.JoinClassDTORes;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.IJoinClassService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/joinClass")

public class JoinClassController {
    @Autowired
    private IJoinClassService joinClassService;

    public HttpMessage httpMessage;
    private GenericResponse<JoinClassDTORes> response;
    private GenericResponse<List<JoinClassDTORes>> listResponse;

    public JoinClassController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACADEMIC','HR','PRINCIPAL','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<JoinClassDTORes> findOne(@PathVariable("id") Long id){
        JoinClassDTORes data = joinClassService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    //@PreAuthorize("hasAnyAuthority('ACADEMIC','HR','PRINCIPAL','ADMIN')")
    //@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<JoinClassDTORes>> findAll(){
        return listResponse.success(joinClassService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('ACADEMIC','HR','PRINCIPAL','USER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<JoinClassDTORes> create(@RequestBody JoinClassDTO joinClassDTO){
        return response.success(joinClassService.create(joinClassDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACADEMIC','HR','PRINCIPAL','USER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<JoinClassDTORes> update(@PathVariable("id") Long id, @RequestBody JoinClassDTO joinClassDTO){
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(joinClassDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(joinClassDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(joinClassService.update(joinClassDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACADEMIC','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<JoinClassDTORes> delete(@PathVariable("id") Long id){
        joinClassService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }
}
