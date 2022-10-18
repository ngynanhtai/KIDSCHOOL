package com.example.backend.controller;


import com.example.backend.dto.ClassDTO;
import com.example.backend.dtoresponse.ClassDTORes;
import com.example.backend.dtoresponse.EmployeeDTORes;
import com.example.backend.dtoresponse.StudentDTORes;
import com.example.backend.dtoresponse.SubjectDTORes;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.IClassService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
//M2M :
// classID/subject/remove -> Checked METHOD POST
// classID/subjectList -> Checked METHOD GET
// classID/subject -> Checked METHOD PUT

// classID/student/remove -> Checked METHOD POST/PUT
// classID/student -> Checked METHOD PUT
// classID/studentList ->  Checked METHOD GET

// classID/employee/remove -> Checked METHOD POST
// classID/employee -> Checked METHOD PUT
// classID/employeeList -> Checked METHOD GET
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/class")
public class ClassController {
    @Autowired
    private IClassService classService;

    public HttpMessage httpMessage;
    private GenericResponse<ClassDTORes> response;
    private GenericResponse<List<ClassDTORes>> listResponse;

    public ClassController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('TEACHER','PRINCIPAL','ACADEMIC','STUDENT','HR','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<ClassDTORes> findOne(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        ClassDTORes data = classService.findOne(id);
        return data != null
                ? response.success(data, httpMessage.GET_SUCCESS)
                : response.error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}/studentList/")
    @PreAuthorize("hasAnyAuthority('TEACHER','PRINCIPAL','ACADEMIC','HR','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<StudentDTORes>> findAllStudentByClass(@PathVariable("id") Long id) throws UnsupportedEncodingException {
            List<StudentDTORes> studentDTOList = classService.findAllStudentByClassID(id);
            if(studentDTOList == null){
                return new GenericResponse<List<StudentDTORes>>().error(null, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
            }
            if(studentDTOList.size() == 0){
                return new GenericResponse<List<StudentDTORes>>().success(new ArrayList<StudentDTORes>(), "No Data");
            }
            return new GenericResponse<List<StudentDTORes>>().success(studentDTOList, httpMessage.GET_SUCCESS);
    }

    @GetMapping("/{id}/employeeList/")
    @PreAuthorize("hasAnyAuthority('PRINCIPAL','ACADEMIC','HR','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<EmployeeDTORes>> findAllEmployeeByClass(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        List<EmployeeDTORes> employeeDTOList = classService.findAllEmployeeByClassID(id);
        if(employeeDTOList.size() == 0){
            return new GenericResponse<List<EmployeeDTORes>>().error(null, "No Data", HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<EmployeeDTORes>>().success(employeeDTOList, httpMessage.GET_SUCCESS);
    }

    @GetMapping("/{id}/subjectList/")
    @PreAuthorize("hasAnyAuthority('TEACHER','PRINCIPAL','ACADEMIC','HR','ADMIN','USER')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<SubjectDTORes>> findAllSubjectByClass(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        List<SubjectDTORes> subjectDTOList = classService.findAllSubjectByClassID(id);
        if(subjectDTOList.size() == 0){
            return new GenericResponse<List<SubjectDTORes>>().error(null, "No Data", HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<SubjectDTORes>>().success(subjectDTOList, httpMessage.GET_SUCCESS);
    }

    @GetMapping("/")
//    @PreAuthorize("hasAnyAuthority('TEACHER','PRINCIPAL','ACADEMIC','HR','STUDENT','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<ClassDTORes>> findAll() throws UnsupportedEncodingException {
        return listResponse.success(classService.findAll(), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<ClassDTORes> create(@RequestBody ClassDTO classDTO){
        try{
            ClassDTORes result = classService.create(classDTO);
            return response.success(result, httpMessage.CREATE_SUCCESS);
        }catch (Exception ex){
            return response.error(null, ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<ClassDTORes> update(@PathVariable("id") Long id, @RequestBody ClassDTO classDTO) throws UnsupportedEncodingException {
        if(id.toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(!id.equals(classDTO.getId())){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(classDTO.getId().toString().isEmpty()){
            return response.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return response.success(classService.update(classDTO), httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACADEMIC','ADMIN')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<ClassDTORes> delete(@PathVariable("id") Long id){
        classService.delete(id);
        return response.success(null, httpMessage.DELETE_SUCCESS);
    }

    @PutMapping("/{id}/student/")
    @PreAuthorize("hasAnyAuthority('ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<StudentDTORes>> addStudentByClassId(@PathVariable("id") Long id, @RequestBody List<Long> idList) throws UnsupportedEncodingException {
        GenericResponse<List<StudentDTORes>> res = new GenericResponse<>();
        List<StudentDTORes> studentDTOList = classService.updateStudentForClass(id, idList);
        return studentDTOList.isEmpty() ? res.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST) : res.success(studentDTOList, httpMessage.UPDATE_SUCCESS);
    }

    @PutMapping("/{id}/employee/")
    @PreAuthorize("hasAnyAuthority('ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<EmployeeDTORes>> addEmployeeByClassId(@PathVariable("id") Long id, @RequestBody List<Long> idList) throws UnsupportedEncodingException {
        GenericResponse<List<EmployeeDTORes>> res = new GenericResponse<>();
        List<EmployeeDTORes> employeeDTOList = classService.updateEmployeeForClass(id, idList);
        return employeeDTOList.isEmpty() ? res.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST) : res.success(employeeDTOList, httpMessage.UPDATE_SUCCESS);
    }

    @PutMapping("/{id}/subject/")
    @PreAuthorize("hasAnyAuthority('ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<SubjectDTORes>> addSubjectByClassId(@PathVariable("id") Long id, @RequestBody List<Long> idList) throws UnsupportedEncodingException {
        GenericResponse<List<SubjectDTORes>> res = new GenericResponse<>();
        List<SubjectDTORes> subjectDTOList = classService.updateSubjectForClass(id, idList);
        return subjectDTOList.isEmpty() ? res.error(null, httpMessage.UPDATE_FAIL, HttpStatus.BAD_REQUEST) : res.success(subjectDTOList, httpMessage.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}/student/remove")
    @PreAuthorize("hasAuthority('ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<StudentDTORes>> removeStudentByClassID(@PathVariable("id") Long id, @RequestBody List<Long> ids) throws UnsupportedEncodingException {
        List<StudentDTORes> result = classService.removeStudentByClassID(id, ids);
        if(result == null){
            return new GenericResponse<List<StudentDTORes>>().error(null, httpMessage.DELETE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<StudentDTORes>>().success(result, httpMessage.DELETE_SUCCESS);
    }

    @DeleteMapping("/{id}/employee/remove")
    @PreAuthorize("hasAuthority('ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<EmployeeDTORes>> removeEmployeeByClassID(@PathVariable("id") Long id, @RequestBody List<Long> ids) throws UnsupportedEncodingException {
        List<EmployeeDTORes> result = classService.removeEmployeeByClassID(id, ids);
        if(result == null){
            return new GenericResponse<List<EmployeeDTORes>>().error(null, httpMessage.DELETE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<EmployeeDTORes>>().success(result, httpMessage.DELETE_SUCCESS);
    }

    @DeleteMapping("/{id}/subject/remove")
    @PreAuthorize("hasAuthority('ACADEMIC')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT_token")
    public GenericResponse<List<SubjectDTORes>> removeSubjectByClassID(@PathVariable("id") Long id, @RequestBody List<Long> ids) throws UnsupportedEncodingException {
        List<SubjectDTORes> result = classService.removeSubjectByClassID(id, ids);
        if(result == null){
            return new GenericResponse<List<SubjectDTORes>>().error(null, httpMessage.DELETE_FAIL, HttpStatus.BAD_REQUEST);
        }
        return new GenericResponse<List<SubjectDTORes>>().success(result, httpMessage.DELETE_SUCCESS);
    }
}
