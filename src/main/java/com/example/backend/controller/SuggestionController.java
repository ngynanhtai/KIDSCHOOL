package com.example.backend.controller;

import com.example.backend.dto.SuggestionDTO;
import com.example.backend.dtoresponse.SuggestionDTORes;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.ISuggestionService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/suggestion")
public class SuggestionController {
    @Autowired
    private ISuggestionService suggestionService;

    public HttpMessage httpMessage;
    private GenericResponse<SuggestionDTORes> response;
    private GenericResponse<List<SuggestionDTORes>> listResponse;

    public SuggestionController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('TEACHER') or hasAuthority('HR') or hasAuthority('ADMIN') or hasAuthority('PRINCIPAL')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SuggestionDTORes> findOne(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        SuggestionDTORes data = suggestionService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('TEACHER') or hasAuthority('HR') or hasAuthority('ADMIN') or hasAuthority('PRINCIPAL')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<SuggestionDTORes>> findAll() throws UnsupportedEncodingException {
        return listResponse.success(suggestionService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('TEACHER') or hasAuthority('HR') or hasAuthority('ADMIN') or hasAuthority('PRINCIPAL')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SuggestionDTORes> create(@RequestBody SuggestionDTO suggestionDTO) throws UnsupportedEncodingException {
        return response.success(suggestionService.create(suggestionDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('HR') or hasAuthority('ADMIN') or hasAuthority('PRINCIPAL') or hasAuthority('TEACHER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SuggestionDTORes> update(@PathVariable("id") Long id, @RequestBody SuggestionDTO suggestionDTO) throws UnsupportedEncodingException {
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(suggestionDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(suggestionDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(suggestionService.update(suggestionDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('HR') or hasAuthority('ADMIN') or hasAuthority('PRINCIPAL') or hasAuthority('TEACHER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SuggestionDTORes> delete(@PathVariable("id") Long id){
        suggestionService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }
}
