package com.example.backend.controller;

import com.example.backend.dto.ParentDTO;
import com.example.backend.dtoresponse.ParentDTORes;
import com.example.backend.model.ParentEntity;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.IParentService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/parent")
public class ParentController {
    @Autowired
    private IParentService parentService;

    public HttpMessage httpMessage;
    private GenericResponse<ParentDTORes> response;
    private GenericResponse<List<ParentDTORes>> listResponse;

    public ParentController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TEACHER') or hasAuthority('USER') or hasAuthority('ACADEMIC') or hasAuthority('ADMIN') or hasAuthority('HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<ParentDTORes> findOne(@PathVariable("id") Long id){
        ParentDTORes data = parentService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('TEACHER') or hasAuthority('USER') or hasAuthority('ACADEMIC') or hasAuthority('ADMIN') or hasAuthority('HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<ParentDTORes>> findAll(){
        return listResponse.success(parentService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    //@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    //@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<ParentDTORes> create(@RequestBody ParentDTO parentDTO){
        return response.success(parentService.create(parentDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<ParentDTORes> update(@PathVariable("id") Long id, @RequestBody ParentDTO parentDTO){
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(parentDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(parentDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(parentService.update(parentDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<ParentDTORes> delete(@PathVariable("id") Long id){
        parentService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }
}
