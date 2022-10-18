package com.example.backend.controller;

import com.example.backend.dto.TypeSubjectDTO;
import com.example.backend.dtoresponse.TypeSubjectDTORes;
import com.example.backend.model.TypeSubjectEntity;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.ITypeSubjectService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/typeSubject")
public class TypeSubjectController {
    @Autowired
    private ITypeSubjectService typeSubjectService;

    public HttpMessage httpMessage;
    private GenericResponse<TypeSubjectDTORes> response;
    private GenericResponse<List<TypeSubjectDTORes>> listResponse;

    public TypeSubjectController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    public GenericResponse<TypeSubjectDTORes> findOne(@PathVariable("id") Long id){
        TypeSubjectDTORes data = typeSubjectService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    public GenericResponse<List<TypeSubjectDTORes>> findAll(){
        return listResponse.success(typeSubjectService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<TypeSubjectDTORes> create(@RequestBody TypeSubjectDTO typeSubjectDTO){
        return response.success(typeSubjectService.create(typeSubjectDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<TypeSubjectDTORes> update(@PathVariable("id") Long id, @RequestBody TypeSubjectDTO typeSubjectDTO){
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(typeSubjectDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(typeSubjectDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(typeSubjectService.update(typeSubjectDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<TypeSubjectDTORes> delete(@PathVariable("id") Long id){
        typeSubjectService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }
}
