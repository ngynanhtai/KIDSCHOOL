package com.example.backend.controller;

import com.example.backend.dto.FeedbackDTO;
import com.example.backend.dtoresponse.FeedbackDTORes;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.IFeedbackService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private IFeedbackService feedbackService;

    public HttpMessage httpMessage;
    private GenericResponse<FeedbackDTORes> response;
    private GenericResponse<List<FeedbackDTORes>> listResponse;

    public FeedbackController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PRINCIPAL','ACADEMIC','ADMIN','USER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<FeedbackDTORes> findOne(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        FeedbackDTORes data = feedbackService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
//    @PreAuthorize("hasyAuAnthority('PRINCIPAL','ACADEMIC','ADMIN','TEACHER','USER')")
//    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<FeedbackDTORes>> findAll() throws UnsupportedEncodingException {
        return listResponse.success(feedbackService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
//    @PreAuthorize("hasAnyAuthority('TEACHER','USER')")
//    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<FeedbackDTORes> create(@RequestBody FeedbackDTO feedbackDTO) throws UnsupportedEncodingException {
        return response.success(feedbackService.create(feedbackDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ACADEMIC','ADMIN','TEACHER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<FeedbackDTORes> update(@PathVariable("id") Long id, @RequestBody FeedbackDTO feedbackDTO) throws UnsupportedEncodingException {
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(feedbackDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(feedbackDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(feedbackService.update(feedbackDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACADEMIC','ADMIN','TEACHER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<FeedbackDTORes> delete(@PathVariable("id") Long id){
        feedbackService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }
}
