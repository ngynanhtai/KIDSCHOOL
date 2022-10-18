package com.example.backend.controller;

import com.example.backend.dto.RoleDTO;
import com.example.backend.dtoresponse.AnnouncementDTORes;
import com.example.backend.dtoresponse.RoleDTORes;
import com.example.backend.model.RoleEntity;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.IRoleService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
//M2M:
//roleID/announcement/ -> Checked Method: GET/POST
//roleID/announcement/remove -> Checked Method: POST
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    public HttpMessage httpMessage;
    private GenericResponse<RoleDTORes> response;
    private GenericResponse<List<RoleDTORes>> listResponse;

    public RoleController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<RoleDTORes> findOne(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        RoleDTORes data = roleService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
//    @PreAuthorize("hasAuthority('ADMIN')")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR','ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<RoleDTORes>> findAll() throws UnsupportedEncodingException {
        return new GenericResponse<List<RoleDTORes>>().success(roleService.findAll(), httpMessage.GET_SUCCESS);

    }

    @PostMapping("/")
//    @PreAuthorize("hasAuthority('ADMIN')")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<RoleDTORes> create(@RequestBody RoleDTO roleDTO) throws UnsupportedEncodingException {
        return response.success(roleService.create(roleDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<RoleDTORes> update(@PathVariable("id") Long id, @RequestBody RoleDTO roleDTO) throws UnsupportedEncodingException {
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(roleDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(roleDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(roleService.update(roleDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<RoleDTORes> delete(@PathVariable("id") Long id){
        roleService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }

    @GetMapping("/{id}/announcement")
//    @PreAuthorize("hasAuthority('ADMIN')")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<AnnouncementDTORes>> getAnnouncementsByRoleID(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        List<AnnouncementDTORes> result = roleService.findAnnouncementsByRoleID(id);
        if(result == null){
            return new GenericResponse<List<AnnouncementDTORes>>().error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<AnnouncementDTORes>>().success(result, httpMessage.GET_SUCCESS);
    }

    @PostMapping("/{id}/announcement")
//    @PreAuthorize("hasAuthority('ADMIN')")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<AnnouncementDTORes>> addAnnouncementsByRoleID(@PathVariable("id") Long id,@RequestBody List<Long> ids) throws UnsupportedEncodingException {
        List<AnnouncementDTORes> result = roleService.addAnnouncementsByRoleID(id, ids);
        if(result == null){
            return new GenericResponse<List<AnnouncementDTORes>>().error(null, httpMessage.CREATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<AnnouncementDTORes>>().success(result, httpMessage.CREATE_SUCCESS);
    }

    @PostMapping("/{id}/announcement/remove")
//    @PreAuthorize("hasAuthority('ADMIN')")
    @PreAuthorize("hasAnyAuthority('ADMIN','PRINCIPAL','HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<AnnouncementDTORes>> removeAnnouncementsByRoleID(@PathVariable("id") Long id,@RequestBody List<Long> ids) throws UnsupportedEncodingException {
        List<AnnouncementDTORes> result = roleService.removeAnnouncementsByRoleID(id, ids);
        if(result == null){
            return new GenericResponse<List<AnnouncementDTORes>>().error(null, httpMessage.DELETE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<AnnouncementDTORes>>().success(result, httpMessage.DELETE_SUCCESS);
    }
}
