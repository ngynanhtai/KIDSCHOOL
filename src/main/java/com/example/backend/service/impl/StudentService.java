package com.example.backend.service.impl;

import com.example.backend.converter.StudentConverter;
import com.example.backend.convertresponse.ClassConverterRes;
import com.example.backend.convertresponse.StudentConverterRes;
import com.example.backend.dto.StudentDTO;
import com.example.backend.dto.etc.AccountDTO;
import com.example.backend.dto.etc.ChangePasswordDTO;
import com.example.backend.dtoresponse.ClassDTORes;
import com.example.backend.dtoresponse.StudentDTORes;
import com.example.backend.model.ClassEntity;
import com.example.backend.model.StudentEntity;
import com.example.backend.repository.ClassRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentConverter studentConverter;

    @Autowired
    private StudentConverterRes studentConverterRes;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ClassConverterRes classConverterRes;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public StudentDTORes create(StudentDTO studentDTO) throws UnsupportedEncodingException {
        StudentEntity entity = studentConverter.toEntity(studentDTO);
        entity.setStatus(false);
        entity.setCreateAt(new Date().toString());
        entity.setPassword(encoder.encode(studentDTO.getPassword()));
        studentRepository.save(entity);
        entity.setAccountId(calculateClassId(entity.getId()));
        return studentConverterRes.toDTO(studentRepository.saveAndFlush(entity));
    }

    private String calculateClassId(Long id){
        String DEFAULT_PREFIX = "S";
        String DEFAULT_VALUE = "";
        String idCast = id.toString();
        switch (idCast.length()){
            case 1:
                DEFAULT_VALUE = "00000" + idCast;
                break;
            case 2:
                DEFAULT_VALUE = "0000" + idCast;
                break;
            case 3:
                DEFAULT_VALUE = "000" + idCast;
                break;
            case 4:
                DEFAULT_VALUE = "00" + idCast;
                break;
            case 5:
                DEFAULT_VALUE = "0" + idCast;
                break;
            case 6:
                DEFAULT_VALUE = idCast;
                break;
            default: break;
        }
        return DEFAULT_PREFIX + DEFAULT_VALUE;
    }
    @Override
    public StudentDTORes update(StudentDTO studentDTO) throws UnsupportedEncodingException {
        StudentEntity curr = studentRepository.findById(studentDTO.getId()).get();
        StudentEntity entity = studentConverter.toEntity(curr, studentDTO);
        return studentConverterRes.toDTO(studentRepository.save(entity));
    }

    @Override
    public List<StudentDTORes> findAll() throws UnsupportedEncodingException {
        List<StudentDTORes> result = new ArrayList<>();
        List<StudentEntity> entityList = studentRepository.findAll();
        for (StudentEntity studentEntity : entityList){
            result.add(studentConverterRes.toDTO(studentEntity));
        }
        return result;
    }

    @Override
    public Boolean checkAccountId(String accountId) {
        return studentRepository.findByAccountId(accountId) != null ? true : false;
    }

    @Override
    public List<ClassDTORes> getAllClassByStudentID(Long id) throws UnsupportedEncodingException {
        Optional<StudentEntity> studentEntity = studentRepository.findById(id);
        if(!studentEntity.isPresent()){
            return null;
        }
        StudentEntity curr = studentEntity.get();
        if(curr.getClassEntities().size() == 0){
            return new ArrayList<>();
        }
        List<ClassDTORes> classDTORes = new ArrayList<>();
        for (ClassEntity classEntity : curr.getClassEntities()){
            classDTORes.add(classConverterRes.toDTO(classEntity));
        }
        return classDTORes;
    }

    @Override
    public List<ClassDTORes> addClassForStudent(Long id, List<Long> ids) throws UnsupportedEncodingException {
        Optional<StudentEntity> studentEntity = studentRepository.findById(id);
        if(!studentEntity.isPresent()){
            return null;
        }
        StudentEntity curr = studentEntity.get();
        for (Long classID: ids){
            ClassEntity classEntity = classRepository.findById(classID).get();
            classEntity.getStudentEntities().add(curr);
            classRepository.save(classEntity);
        }
        StudentEntity data = studentRepository.findById(id).get();
        List<ClassDTORes> result = new ArrayList<>();
        for (ClassEntity classEntity : data.getClassEntities()){
            result.add(classConverterRes.toDTO(classEntity));
        }
        return result;
    }

    @Override
    public List<ClassDTORes> removeClassForStudent(Long id, List<Long> ids) throws UnsupportedEncodingException {
        Optional<StudentEntity> studentEntity = studentRepository.findById(id);
        if(!studentEntity.isPresent()){
            return null;
        }
        StudentEntity curr = studentEntity.get();
        for (Long classID: ids){
            ClassEntity  classEntity = classRepository.findById(classID).get();
            classEntity.getStudentEntities().remove(curr);
            classRepository.save(classEntity);
        }
        StudentEntity data = studentRepository.findById(id).get();
        List<ClassDTORes> result = new ArrayList<>();
        for (ClassEntity classEntity : data.getClassEntities()){
            result.add(classConverterRes.toDTO(classEntity));
        }
        return result;
    }

    @Override
    public Boolean changePassword(Long id, ChangePasswordDTO changePasswordDTO) {
        Optional<StudentEntity> studentEntity = studentRepository.findById(id);
        if(!studentEntity.isPresent()){
            return false;
        }
        StudentEntity curr = studentEntity.get();
        if(encoder.matches(changePasswordDTO.getOldPassword(), curr.getPassword())) {
            curr.setPassword(encoder.encode(changePasswordDTO.getNewPassword()));
            studentRepository.save(curr);
            return true;
        }
        return false;
    }

    @Override
    public Map<String, String> forgotPassword(String username) {
        StudentEntity student = studentRepository.findByAccountId(username);
        Map<String, String> map = new HashMap<>();
        if (student != null) {
            map.put("accountId", student.getAccountId());
            map.put("email", student.getEmail());
            map.put("generatedCode", generateRandomString());
            return map;
        }
        return null;
    }

    @Override
    public String generateRandomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    @Override
    public void forgotPasswordChange(AccountDTO accountDTO) {
        StudentEntity curr = studentRepository.findByAccountId(accountDTO.getUsername());
        curr.setPassword(encoder.encode(accountDTO.getPassword()));
        studentRepository.save(curr);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTORes findOne(Long id) throws UnsupportedEncodingException {
        Optional<StudentEntity> data = studentRepository.findById(id);
        return data.isPresent()
                ? studentConverterRes.toDTO(data.get())
                : null;
    }
}
