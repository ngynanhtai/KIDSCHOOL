package com.example.backend.controller;

import com.example.backend.dto.StudentDTO;
import com.example.backend.dto.etc.AccountDTO;
import com.example.backend.dto.etc.ChangePasswordDTO;
import com.example.backend.dto.etc.EmailDTO;
import com.example.backend.dtoresponse.ClassDTORes;
import com.example.backend.dtoresponse.StudentDTORes;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.IStudentService;
import com.example.backend.service.impl.EmailService;
import com.fasterxml.jackson.databind.node.TextNode;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

//M2M:
//studentID/class/ -> Checked Method: GET/POST
//studentID/class/remove -> Checked Method: POST
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private EmailService emailService;

    public HttpMessage httpMessage;
    private GenericResponse<StudentDTORes> response;
    private GenericResponse<List<StudentDTORes>> listResponse;

    public StudentController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TEACHER') or hasAuthority('USER') or hasAuthority('ACADEMIC') or hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<StudentDTORes> findOne(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        StudentDTORes data = studentService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('TEACHER') or hasAuthority('USER') or hasAuthority('ACADEMIC') or hasAuthority('ADMIN') or hasAuthority('PRINCIPAL') or hasAuthority('HR')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<StudentDTORes>> findAll() throws UnsupportedEncodingException {
        return listResponse.success(studentService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    //@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    //@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<StudentDTORes> create(@RequestBody StudentDTO studentDTO) throws UnsupportedEncodingException {
        return response.success(studentService.create(studentDTO), httpMessage.CREATE_SUCCESS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('TEACHER') or hasAuthority('USER') or hasAuthority('ACADEMIC') or hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<StudentDTORes> update(@PathVariable("id") Long id, @RequestBody StudentDTO studentDTO) throws UnsupportedEncodingException {
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(studentDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(studentDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(studentService.update(studentDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ACADEMIC') or hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<StudentDTORes> delete(@PathVariable("id") Long id){
        studentService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }

    @GetMapping("/{id}/class")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<ClassDTORes>> getClassesByStudentID(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        List<ClassDTORes> result = studentService.getAllClassByStudentID(id);
        if(result == null){
            return new GenericResponse<List<ClassDTORes>>().error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<ClassDTORes>>().success(result, httpMessage.GET_SUCCESS);
    }

    @PostMapping("/{id}/class")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<ClassDTORes>> addClass(@PathVariable("id") Long id, @RequestBody List<Long> ids) throws UnsupportedEncodingException {
        List<ClassDTORes> result = studentService.addClassForStudent(id, ids);
        if(result == null){
            return new GenericResponse<List<ClassDTORes>>().error(null, httpMessage.CREATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<ClassDTORes>>().success(result, httpMessage.CREATE_SUCCESS);
    }

    @PostMapping("/{id}/class/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<ClassDTORes>> removeClass(@PathVariable("id") Long id, @RequestBody List<Long> ids) throws UnsupportedEncodingException {
        List<ClassDTORes> result = studentService.removeClassForStudent(id, ids);
        if(result == null){
            return new GenericResponse<List<ClassDTORes>>().error(null, httpMessage.DELETE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<ClassDTORes>>().success(result, httpMessage.DELETE_SUCCESS);
    }

    @PutMapping("/{id}/changePassword")
    @PreAuthorize("hasAuthority('USER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<Boolean> changePassword(@PathVariable("id") Long id, @RequestBody ChangePasswordDTO changePasswordDTO) {
        return studentService.changePassword(id, changePasswordDTO)
                ? new GenericResponse<Boolean>().success(true, httpMessage.UPDATE_SUCCESS)
                : new GenericResponse<Boolean>().error(false, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/forgotPassword")
    public GenericResponse<?> getGeneratedCode(@RequestBody Map<String, String> data) {
        Map<String, String> map = studentService.forgotPassword(data.get("username"));
        if (map != null) {
            EmailDTO emailDTO = new EmailDTO();
            emailDTO.setSubject("KIDSCHOOL - Change Account Password");
            emailDTO.setMsgBody("Your verification code: " + map.get("generatedCode"));
            emailDTO.setRecipient(map.get("email"));

            return emailService.sendSimpleMail(emailDTO)
                    ? new GenericResponse<Map<String, String>>().success(map, httpMessage.CREATE_SUCCESS)
                    : new GenericResponse<>().error("Error while sending mail!!!", httpMessage.CREATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<>().error("Could not find your username", httpMessage.CREATE_FAIL, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/forgotPassword")
    public GenericResponse<?> forgotPasswordChange(@RequestBody AccountDTO accountDTO) {
        studentService.forgotPasswordChange(accountDTO);
        return new GenericResponse<>().success("Password change successfully", httpMessage.UPDATE_SUCCESS);
    }
}
