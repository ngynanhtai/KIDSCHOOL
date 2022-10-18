package com.example.backend.controller;

import com.example.backend.dto.SchoolDTO;
import com.example.backend.dto.ScoreDTO;
import com.example.backend.dtoresponse.ScoreDTORes;
import com.example.backend.model.ScoreEntity;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.IScoreService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/score")
public class ScoreController {
    @Autowired
    private IScoreService scoreService;

    public HttpMessage httpMessage;
    private GenericResponse<ScoreDTORes> response;
    private GenericResponse<List<ScoreDTORes>> listResponse;

    public ScoreController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TEACHER') or hasAuthority('USER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<ScoreDTORes> findOne(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        ScoreDTORes data = scoreService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('TEACHER') or hasAuthority('USER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<ScoreDTORes>> findAll() throws UnsupportedEncodingException {
        return listResponse.success(scoreService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('TEACHER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<ScoreDTORes> create(@RequestBody ScoreDTO scoreDTO) throws UnsupportedEncodingException {
        return response.success(scoreService.create(scoreDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<ScoreDTORes> update(@PathVariable("id") Long id, @RequestBody ScoreDTO scoreDTO) throws UnsupportedEncodingException {
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(scoreDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(scoreDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(scoreService.update(scoreDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<ScoreDTORes> delete(@PathVariable("id") Long id){
        scoreService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }

    @PostMapping("/update-scores")
    @PreAuthorize("hasAuthority('TEACHER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<Boolean> updateScores(@RequestBody List<ScoreDTO> scoreDTOS){
        try{
            Boolean result = scoreService.updateScores(scoreDTOS);
            return result
                    ? new GenericResponse<Boolean>().success(true, httpMessage.UPDATE_SUCCESS)
                    : new GenericResponse<Boolean>().error(false, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            return new GenericResponse<Boolean>().error(false, ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
