package com.example.backend.controller;

import com.example.backend.dto.etc.EmailDTO;
import com.example.backend.dtoresponse.StudentDTORes;
import com.example.backend.model.utils.GenericResponse;
import com.example.backend.model.utils.HttpMessage;
import com.example.backend.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private IEmailService emailService;

    public HttpMessage httpMessage;
    private GenericResponse<StudentDTORes> response;
    private GenericResponse<List<StudentDTORes>> listResponse;

    public EmailController() {
        response = new GenericResponse<>();
        listResponse = new GenericResponse<>();
        httpMessage = new HttpMessage();
    }

    @PostMapping("/sendMail")
    public GenericResponse<?> sendMail(@RequestBody EmailDTO details) {
        return emailService.sendSimpleMail(details)
                ? new GenericResponse<>().success("Mail sent Successfully !", httpMessage.CREATE_SUCCESS)
                : new GenericResponse<>().error("Error while sending mail!!!", httpMessage.CREATE_FAIL, HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/sendMailWithAttachment")
    public GenericResponse<?> sendMailWithAttachment(@RequestBody EmailDTO details) {
        return emailService.sendMailWithAttachment(details)
                ? new GenericResponse<>().success("Mail sent Successfully !", httpMessage.CREATE_SUCCESS)
                : new GenericResponse<>().error("Error while sending mail!!!", httpMessage.CREATE_FAIL, HttpStatus.BAD_REQUEST);
    }
}
