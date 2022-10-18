package com.example.backend.controller;

import com.example.backend.dto.SymptomDTO;
import com.example.backend.dtoresponse.SymptomDTORes;
import com.example.backend.model.SymptomEntity;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.ISymptomService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/symptom")
public class SymptomController {
    @Autowired
    private ISymptomService symptomService;

    public HttpMessage httpMessage;
    private GenericResponse<SymptomDTORes> response;
    private GenericResponse<List<SymptomDTORes>> listResponse;

    public SymptomController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    public GenericResponse<SymptomDTORes> findOne(@PathVariable("id") Long id){
        SymptomDTORes data = symptomService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    public GenericResponse<List<SymptomDTORes>> findAll(){
        return listResponse.success(symptomService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SymptomDTORes> create(@RequestBody SymptomDTO symptomDTO){
        return response.success(symptomService.create(symptomDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SymptomDTORes> update(@PathVariable("id") Long id, @RequestBody SymptomDTO symptomDTO){
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(symptomDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(symptomDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(symptomService.update(symptomDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SymptomDTORes> delete(@PathVariable("id") Long id){
        symptomService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }
}
