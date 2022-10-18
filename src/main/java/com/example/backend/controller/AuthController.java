package com.example.backend.controller;

import com.example.backend.dto.etc.AccountDTO;
import com.example.backend.dto.StudentDTO;
import com.example.backend.dtoresponse.StudentDTORes;
import com.example.backend.jwt.JwtUtils;
import com.example.backend.jwt.UserDetailsImpl;
import com.example.backend.jwt.dto.JwtResponse;
import com.example.backend.model.EmployeeEntity;
import com.example.backend.model.StudentEntity;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.IEmployeeService;
import com.example.backend.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    public HttpMessage httpMessage;
    private GenericResponse<StudentDTORes> response;

    public AuthController() {
        response = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @PostMapping("/signin")
    public GenericResponse<?> authenticateUser(@Validated @RequestBody AccountDTO loginRequest) throws UnsupportedEncodingException {
        StudentEntity studentAccount = studentRepository.findByAccountId(loginRequest.getUsername());
        EmployeeEntity employeeAccount = employeeRepository.findByAccountId(loginRequest.getUsername());
        if(studentAccount == null && employeeAccount == null) {
            return new GenericResponse<>().error(false, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(studentAccount != null && !encoder.matches(loginRequest.getPassword(), studentAccount.getPassword())) {
            return new GenericResponse<>().error(false, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
        }
        if(employeeAccount != null && !encoder.matches(loginRequest.getPassword(), employeeAccount.getPassword())) {
            return new GenericResponse<>().error(false, httpMessage.GET_FAIL, HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = new ArrayList<>();
        String role = "";
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.add(authority.getAuthority());
            role = authority.getAuthority();
        }
        return ("USER").equals(role)
                ? new GenericResponse<JwtResponse>().success(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getPassword(), roles, studentService.findOne(userDetails.getId())), httpMessage.GET_SUCCESS)
                : new GenericResponse<JwtResponse>().success(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getPassword(), roles, employeeService.findOne(userDetails.getId())), httpMessage.GET_SUCCESS);
    }

    @PostMapping("/signup")
    public GenericResponse<StudentDTORes> registerUser(@Validated @RequestBody StudentDTO registerRequest) throws UnsupportedEncodingException {
        registerRequest.setRole_id(1L);
        //dùng hàm này chỉ để tạo user account, nên mặc định role sẽ là user vì mấy role khác phải đợi được người có quyền set
        return response.success(studentService.create(registerRequest), httpMessage.CREATE_SUCCESS);
    }
}
