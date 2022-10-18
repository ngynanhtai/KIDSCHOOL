package com.example.backend.controller;

import com.example.backend.dto.TypeClassDTO;
import com.example.backend.dtoresponse.TypeClassDTORes;
import com.example.backend.model.RoleEntity;
import com.example.backend.model.TypeClassEntity;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.IRoleService;
import com.example.backend.service.ITypeClassService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/typeClass")
public class TypeClassController {
    @Autowired
    private ITypeClassService typeClassService;

    public HttpMessage httpMessage;
    private GenericResponse<TypeClassDTORes> response;
    private GenericResponse<List<TypeClassDTORes>> listResponse;

    public TypeClassController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    public GenericResponse<TypeClassDTORes> findOne(@PathVariable("id") Long id){
        TypeClassDTORes data = typeClassService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    public GenericResponse<List<TypeClassDTORes>> findAll(){
        return listResponse.success(typeClassService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<TypeClassDTORes> create(@RequestBody TypeClassDTO typeClassDTO){
        return response.success(typeClassService.create(typeClassDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<TypeClassDTORes> update(@PathVariable("id") Long id, @RequestBody TypeClassDTO typeClassDTO){
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(typeClassDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(typeClassDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(typeClassService.update(typeClassDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<TypeClassDTORes> delete(@PathVariable("id") Long id){
        typeClassService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }
}
