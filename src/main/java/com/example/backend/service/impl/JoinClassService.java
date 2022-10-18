package com.example.backend.service.impl;

import com.example.backend.converter.JoinClassConverter;
import com.example.backend.convertresponse.JoinClassConverterRes;
import com.example.backend.dto.JoinClassDTO;
import com.example.backend.dto.etc.EmailDTO;
import com.example.backend.dtoresponse.JoinClassDTORes;
import com.example.backend.model.JoinClassEntity;
import com.example.backend.repository.JoinClassRepository;
import com.example.backend.service.IEmailService;
import com.example.backend.service.IJoinClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JoinClassService implements IJoinClassService {

    private JoinClassEntity entity;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private JoinClassRepository joinClassRepository;

    @Autowired
    private JoinClassConverter joinClassConverter;

    @Autowired
    private JoinClassConverterRes joinClassConverterRes;

    @Override
    public JoinClassDTORes findOne(Long id) {
        Optional<JoinClassEntity> data = joinClassRepository.findById(id);
        return data.isPresent()
                ? joinClassConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public JoinClassDTORes create(JoinClassDTO joinClassDTO) {
        entity = joinClassConverter.toEntity(joinClassDTO);
        entity.setStatus(false);
        JoinClassEntity afterSave = joinClassRepository.save(entity);
        JoinClassDTORes res = joinClassConverterRes.toDTO(afterSave);
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setSubject("KIDSCHOOL - Class Information");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Successfully registered !" + System.getProperty("line.separator"));
        stringBuilder.append("Hi, " + afterSave.getStudentEntity().getFirstName() + " " + afterSave.getStudentEntity().getLastName() + " and parents" + System.getProperty("line.separator"));
        stringBuilder.append("Thank you for joining us, here is the information you will need to know" + System.getProperty("line.separator"));
        stringBuilder.append("Class: " + afterSave.getClassEntity().getName() + System.getProperty("line.separator"));
        stringBuilder.append("Start At: " + convertMillisecondToDate(afterSave.getClassEntity().getStartDate()) + " - End At: " + convertMillisecondToDate(afterSave.getClassEntity().getEndDate()) + System.getProperty("line.separator"));
        stringBuilder.append("Fee: " + afterSave.getClassEntity().getFee() + System.getProperty("line.separator"));
        stringBuilder.append("Please arrive school before 7 days to pay the school fee " + System.getProperty("line.separator"));
        stringBuilder.append("School Address: " + afterSave.getSchoolEntity().getAddress());
        emailDTO.setMsgBody(stringBuilder.toString());
        emailDTO.setRecipient(afterSave.getStudentEntity().getEmail());
        emailService.sendSimpleMail(emailDTO);
        return res;
    }

    @Override
    public void delete(Long id) {
        joinClassRepository.deleteById(id);
    }

    @Override
    public JoinClassDTORes update(JoinClassDTO joinClassDTO) {
        JoinClassEntity currEntity = joinClassRepository.findById(joinClassDTO.getId()).get();
        entity = joinClassConverter.toEntity(currEntity, joinClassDTO);
        return joinClassConverterRes.toDTO(joinClassRepository.save(entity));
    }

    @Override
    public List<JoinClassDTORes> findAll() {
        List<JoinClassDTORes> result = new ArrayList<>();
        List<JoinClassEntity> entityList = joinClassRepository.findAll();
        for (JoinClassEntity item : entityList) {
            result.add(joinClassConverterRes.toDTO(item));
        }
        return result;
    }

    private String convertMillisecondToDate(String millisecond) {
        LocalDate localDate = Instant.ofEpochMilli(Long.parseLong(millisecond)).atZone(ZoneId.of("UTC")).toLocalDate();
        DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("u-M-d");
        return localDate.format(formatter);
    }
}
