package com.example.backend.controller;


import com.example.backend.dto.AnnouncementDTO;
import com.example.backend.dto.RoleDTO;
import com.example.backend.dtoresponse.AnnouncementDTORes;
import com.example.backend.dtoresponse.RoleDTORes;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.IAnnouncementService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
//API M2M Announcement - Role -> Checked
//announceID/role/ -> Checked METHOD POST/GET
//announceID/role/remove -> Checked METHOD POST
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController {
    @Autowired
    private IAnnouncementService announcementService;
    public HttpMessage httpMessage;
    private GenericResponse<AnnouncementDTORes> response;
    private GenericResponse<List<AnnouncementDTORes>> listResponse;

    public AnnouncementController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR','ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<AnnouncementDTORes> findOne(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        AnnouncementDTORes data = announcementService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR','ACADEMIC','TEACHER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<AnnouncementDTORes>> findAll() throws UnsupportedEncodingException {
        return listResponse.success(announcementService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR','ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<AnnouncementDTORes> create(@RequestBody AnnouncementDTO announcementDTO) throws UnsupportedEncodingException {
        return response.success(announcementService.create(announcementDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR','ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<AnnouncementDTORes> update(@PathVariable("id") Long id, @RequestBody AnnouncementDTO announcementDTO) throws UnsupportedEncodingException {
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(announcementDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(announcementDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(announcementService.update(announcementDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR','ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<AnnouncementDTORes> delete(@PathVariable("id") Long id){
        announcementService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }

    @PostMapping("/{id}/role")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR','ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<RoleDTORes>> addRole(@PathVariable("id") Long id, @RequestBody List<Long> ids) throws UnsupportedEncodingException {
        List<RoleDTORes> result = announcementService.addRoleForAnnouncement(id, ids);
        if(result == null){
            return new GenericResponse<List<RoleDTORes>>().error(null, httpMessage.CREATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<RoleDTORes>>().success(result, httpMessage.CREATE_SUCCESS);
    }

    @PostMapping("/{id}/role/remove")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR','ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<RoleDTORes>> removeRole(@PathVariable("id") Long id, @RequestBody List<Long> ids) throws UnsupportedEncodingException {
        List<RoleDTORes> result = announcementService.removeRoleForAnnouncement(id, ids);
        if(result == null){
            return new GenericResponse<List<RoleDTORes>>().error(null, httpMessage.CREATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<RoleDTORes>>().success(result, httpMessage.CREATE_SUCCESS);
    }

    @GetMapping("/{id}/role")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR','ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<RoleDTORes>> getRolesByAnnouncementID(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        List<RoleDTORes> result = announcementService.getRolesByAnnouncementID(id);
        if(result == null){
            return new GenericResponse<List<RoleDTORes>>().error(null, httpMessage.CREATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<RoleDTORes>>().success(result, httpMessage.CREATE_SUCCESS);
    }
}
