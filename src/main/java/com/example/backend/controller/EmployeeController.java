package com.example.backend.controller;


import com.example.backend.dto.EmployeeDTO;
import com.example.backend.dto.etc.ChangePasswordDTO;
import com.example.backend.dtoresponse.ClassDTORes;
import com.example.backend.dtoresponse.EmployeeDTORes;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.IEmployeeService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
//M2M:
//employeeID/class/ -> Checked Method: GET/POST
//employeeID/class/remove -> Checked Method: POST
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    public HttpMessage httpMessage;
    private GenericResponse<EmployeeDTORes> response;
    private GenericResponse<List<EmployeeDTORes>> listResponse;

    public EmployeeController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('HR','TEACHER','ACADEMIC','PRINCIPAL','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<EmployeeDTORes> findOne(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        EmployeeDTORes data = employeeService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/")
//    @PreAuthorize("hasAnyAuthority('HR','PRINCIPAL','ADMIN','ACADEMIC')")
//    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<EmployeeDTORes>> findAll() throws UnsupportedEncodingException {
        return listResponse.success(employeeService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('HR','PRINCIPAL','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<EmployeeDTORes> create(@RequestBody EmployeeDTO employeeDTO){
        try{
            return response.success(employeeService.create(employeeDTO), httpMessage.CREATE_SUCCESS);
        }catch (Exception ex){
            return response.error(null, ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('HR','PRINCIPAL','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<EmployeeDTORes> update(@PathVariable("id") Long id, @RequestBody EmployeeDTO employeeDTO) throws UnsupportedEncodingException {
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(employeeDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(employeeDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(employeeService.update(employeeDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PRINCIPAL','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<EmployeeDTORes> delete(@PathVariable("id") Long id){
        employeeService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }

    @GetMapping("/{id}/class/")
    @PreAuthorize("hasAnyAuthority('HR','TEACHER','ACADEMIC','PRINCIPAL','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<ClassDTORes>> getClass(@PathVariable("id") Long id) throws UnsupportedEncodingException {
            List<ClassDTORes> result = employeeService.findAllClassByTeacherID(id);
            if(result == null){
                return new GenericResponse<List<ClassDTORes>>().error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
            }
            return new GenericResponse<List<ClassDTORes>>().success(result, httpMessage.GET_SUCCESS);
    }

    @PostMapping("/{id}/class/")
    @PreAuthorize("hasAnyAuthority('HR','TEACHER','ACADEMIC','PRINCIPAL','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<ClassDTORes>> addClassForTeacher(@PathVariable("id") Long id, @RequestBody List<Long> ids) throws UnsupportedEncodingException {
        List<ClassDTORes> result = employeeService.addClassForTeacher(id, ids);
        if(result == null){
            return new GenericResponse<List<ClassDTORes>>().error(null, httpMessage.CREATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<ClassDTORes>>().success(result, httpMessage.CREATE_SUCCESS);
    }

    @PostMapping("/{id}/class/remove")
    @PreAuthorize("hasAnyAuthority('PRINCIPAL','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<ClassDTORes>> removeClassForTeacher(@PathVariable("id") Long id,@RequestBody List<Long> ids) throws UnsupportedEncodingException {
        List<ClassDTORes> result = employeeService.removeClassForTeacher(id, ids);
        if(result == null){
            return new GenericResponse<List<ClassDTORes>>().error(null, httpMessage.DELETE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<ClassDTORes>>().success(result, httpMessage.DELETE_SUCCESS);
    }

    @PutMapping("/{id}/changePassword")
    @PreAuthorize("hasAnyAuthority('PRINCIPAL','HR','ACADEMIC','TEACHER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<Boolean> changePassword(@PathVariable("id") Long id, @RequestBody ChangePasswordDTO changePasswordDTO) {
        return employeeService.changePassword(id, changePasswordDTO)
                ? new GenericResponse<Boolean>().success(true, httpMessage.UPDATE_SUCCESS)
                : new GenericResponse<Boolean>().error(false, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
    }

}
