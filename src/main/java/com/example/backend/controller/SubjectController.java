package com.example.backend.controller;

import com.example.backend.dto.SubjectDTO;
import com.example.backend.dtoresponse.ClassDTORes;
import com.example.backend.dtoresponse.SubjectDTORes;
import com.example.backend.model.SubjectEntity;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.ISubjectService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
//M2M:
//subjectID/class/ -> Checked Method: GET/POST
//subjectID/class/remove -> Checked Method: POST
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    private ISubjectService subjectService;

    public HttpMessage httpMessage;
    private GenericResponse<SubjectDTORes> response;
    private GenericResponse<List<SubjectDTORes>> listResponse;

    public SubjectController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    public GenericResponse<SubjectDTORes> findOne(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        SubjectDTORes data = subjectService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    public GenericResponse<List<SubjectDTORes>> findAll() throws UnsupportedEncodingException {
        return listResponse.success(subjectService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SubjectDTORes> create(@RequestBody SubjectDTO subjectDTO) throws UnsupportedEncodingException {
        return response.success(subjectService.create(subjectDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SubjectDTORes> update(@PathVariable("id") Long id, @RequestBody SubjectDTO subjectDTO) throws UnsupportedEncodingException {
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(subjectDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(subjectDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(subjectService.update(subjectDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<SubjectDTORes> delete(@PathVariable("id") Long id){
        subjectService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }

    @GetMapping("/{id}/class")
    @PreAuthorize("hasAuthority('ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<ClassDTORes>> getClassesBySubjectID(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        List<ClassDTORes> result = subjectService.getClassesBySubjectID(id);
        if(result == null){
            return new GenericResponse<List<ClassDTORes>>().error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<ClassDTORes>>().success(result, httpMessage.GET_SUCCESS);
    }

    @PostMapping("/{id}/class")
    @PreAuthorize("hasAuthority('ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<ClassDTORes>> addClassesBySubjectID(@PathVariable("id") Long id,@RequestBody List<Long> ids) throws UnsupportedEncodingException {
        List<ClassDTORes> result = subjectService.addClassesBySubjectID(id, ids);
        if(result == null){
            return new GenericResponse<List<ClassDTORes>>().error(null, httpMessage.CREATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<ClassDTORes>>().success(result, httpMessage.CREATE_SUCCESS);
    }

    @PostMapping("/{id}/class/remove")
    @PreAuthorize("hasAuthority('ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<ClassDTORes>> removeClassesBySubjectID(@PathVariable("id") Long id,@RequestBody List<Long> ids) throws UnsupportedEncodingException {
        List<ClassDTORes> result = subjectService.removeClassesBySubjectID(id, ids);
        if(result == null){
            return new GenericResponse<List<ClassDTORes>>().error(null, httpMessage.DELETE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<ClassDTORes>>().success(result, httpMessage.DELETE_SUCCESS);
    }
}
