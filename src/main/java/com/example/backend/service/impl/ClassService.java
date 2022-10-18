package com.example.backend.service.impl;

import com.example.backend.converter.ClassConverter;
import com.example.backend.converter.EmployeeConverter;
import com.example.backend.converter.StudentConverter;
import com.example.backend.converter.SubjectConverter;
import com.example.backend.convertresponse.ClassConverterRes;
import com.example.backend.convertresponse.EmployeeConverterRes;
import com.example.backend.convertresponse.StudentConverterRes;
import com.example.backend.convertresponse.SubjectConverterRes;
import com.example.backend.dto.ClassDTO;
import com.example.backend.dto.EmployeeDTO;
import com.example.backend.dto.StudentDTO;
import com.example.backend.dto.SubjectDTO;
import com.example.backend.dtoresponse.ClassDTORes;
import com.example.backend.dtoresponse.EmployeeDTORes;
import com.example.backend.dtoresponse.StudentDTORes;
import com.example.backend.dtoresponse.SubjectDTORes;
import com.example.backend.model.ClassEntity;
import com.example.backend.model.EmployeeEntity;
import com.example.backend.model.StudentEntity;
import com.example.backend.model.SubjectEntity;
import com.example.backend.model.utils.GsonParserUtils;
import com.example.backend.repository.ClassRepository;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.repository.SubjectRepository;
import com.example.backend.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassService implements IClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ClassConverter classConverter;

    @Autowired
    private StudentConverter studentConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private SubjectConverter subjectConverter;

    @Autowired
    private ClassConverterRes classConverterRes;

    @Autowired
    private StudentConverterRes studentConverterRes;

    @Autowired
    private EmployeeConverterRes employeeConverterRes;

    @Autowired
    private SubjectConverterRes subjectConverterRes;

    @Override
    public ClassDTORes findOne(Long id) throws UnsupportedEncodingException {
        Optional<ClassEntity> data = classRepository.findById(id);
        return data.isPresent()
                ? classConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public ClassDTORes create(ClassDTO classDTO) throws UnsupportedEncodingException {
        ClassEntity entity = classConverter.toEntity(classDTO);
        entity.setStatus(false);
        classRepository.save(entity);
        if(!entity.getId().toString().isEmpty()){
            entity.setClassId(calculateClassId(entity.getId()));
            classRepository.saveAndFlush(entity);
            if(entity != null){
                return classConverterRes.toDTO(entity);
            }
        }
        return null;
    }

    private String calculateClassId(Long id){
        String DEFAULT_PREFIX = "C";
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
    public void delete(Long id) {
        classRepository.deleteById(id);
    }

    @Override
    public ClassDTORes update(ClassDTO classDTO) throws UnsupportedEncodingException {
        ClassEntity currEntity = classRepository.findById(classDTO.getId()).get();
        ClassEntity entity = classConverter.toEntity(currEntity, classDTO);
        return classConverterRes.toDTO(classRepository.save(entity));
    }

    @Override
    public List<ClassDTORes> findAll() throws UnsupportedEncodingException {
        List<ClassDTORes> result = new ArrayList<>();
        List<ClassEntity> entityList = classRepository.findAll();
        for (ClassEntity item : entityList) {
            result.add(classConverterRes.toDTO(item));
        }
        return result;
    }

    @Override
    public List<StudentDTORes> findAllStudentByClassID(Long id) throws UnsupportedEncodingException {
        Optional<ClassEntity> optCur = classRepository.findById(id);
        if(!optCur.isPresent()) return new ArrayList<StudentDTORes>();

        ClassEntity curr = optCur.get();
        List<StudentEntity> entityList = curr.getStudentEntities();
        if(entityList.size() == 0){
            return new ArrayList<StudentDTORes>();
        }
        List<StudentDTORes> result = new ArrayList<>();
        for (StudentEntity studentEntity : entityList){
            result.add(studentConverterRes.toDTO(studentEntity));
        }
        return result;
    }

    @Override
    public List<EmployeeDTORes> findAllEmployeeByClassID(Long id) throws UnsupportedEncodingException {
        Optional<ClassEntity> optCur = classRepository.findById(id);
        if(!optCur.isPresent()) return new ArrayList<EmployeeDTORes>();

        ClassEntity curr = optCur.get();
        List<EmployeeEntity> entityList = curr.getEmployeeEntities();
        if(entityList.size() == 0){
            return new ArrayList<EmployeeDTORes>();
        }
        List<EmployeeDTORes> result = new ArrayList<>();
        for (EmployeeEntity employeeEntity : entityList){
            result.add(employeeConverterRes.toDTO(employeeEntity));
        }
        return result;
    }

    @Override
    public List<SubjectDTORes> findAllSubjectByClassID(Long id) throws UnsupportedEncodingException {
        Optional<ClassEntity> optCur = classRepository.findById(id);
        if(!optCur.isPresent()) return new ArrayList<SubjectDTORes>();

        ClassEntity curr = optCur.get();
        List<SubjectEntity> entityList = curr.getSubjectEntities();
        if(entityList.size() == 0){
            return new ArrayList<SubjectDTORes>();
        }
        List<SubjectDTORes> result = new ArrayList<>();
        for (SubjectEntity subjectEntity : entityList){
            result.add(subjectConverterRes.toDTO(subjectEntity));
        }
        return result;
    }

    @Override
    public List<StudentDTORes> updateStudentForClass(Long id, List<Long> idList) throws UnsupportedEncodingException {
        Optional<ClassEntity> classEntity = classRepository.findById(id);
        if(!classEntity.isPresent()){
            return null;
        }
        List<Long> idAlreadyExist = new ArrayList<>();
        List<StudentDTO> studentInClass = findStudentsByClassIDNative(id);
        for (StudentDTO item : studentInClass) {
            idAlreadyExist.add(item.getId());
        }
        idList.removeAll(idAlreadyExist);
        ClassEntity curr = classEntity.get();
        for (Long stuId: idList){
            curr.getStudentEntities().add(studentRepository.findById(stuId).get());
        }
        classRepository.save(curr);
        List<StudentDTORes> result = new ArrayList<>();
        for (StudentEntity studentEntity : curr.getStudentEntities()){
            result.add(studentConverterRes.toDTO(studentEntity));
        }
        return result;
    }

    @Override
    public List<EmployeeDTORes> updateEmployeeForClass(Long id, List<Long> idList) throws UnsupportedEncodingException {
        Optional<ClassEntity> classEntity = classRepository.findById(id);
        if(!classEntity.isPresent()){
            return null;
        }
        List<Long> idAlreadyExist = new ArrayList<>();
        List<EmployeeDTO> employeeInClass = findAllEmployeeByClassIDNative(id);
        for (EmployeeDTO item : employeeInClass) {
            idAlreadyExist.add(item.getId());
        }
        idList.removeAll(idAlreadyExist);
        ClassEntity curr = classEntity.get();
        for (Long empId: idList){
            curr.getEmployeeEntities().add(employeeRepository.findById(empId).get());
        }
        classRepository.save(curr);
        List<EmployeeDTORes> result = new ArrayList<>();
        for (EmployeeEntity employeeEntity : curr.getEmployeeEntities()){
            result.add(employeeConverterRes.toDTO(employeeEntity));
        }
        return result;
    }

    @Override
    public List<SubjectDTORes> updateSubjectForClass(Long id, List<Long> idList) throws UnsupportedEncodingException {
        Optional<ClassEntity> classEntity = classRepository.findById(id);
        if(!classEntity.isPresent()){
            return null;
        }
        List<Long> idAlreadyExist = new ArrayList<>();
        List<SubjectDTO> subjectInClass = findAllSubjectByClassIDNative(id);
        for (SubjectDTO item : subjectInClass) {
            idAlreadyExist.add(item.getId());
        }
        idList.removeAll(idAlreadyExist);
        ClassEntity curr = classEntity.get();
        for (Long subjectId: idList){
            curr.getSubjectEntities().add(subjectRepository.findById(subjectId).get());
        }
        classRepository.save(curr);
        List<SubjectDTORes> result = new ArrayList<>();
        for (SubjectEntity subjectEntity : curr.getSubjectEntities()){
            result.add(subjectConverterRes.toDTO(subjectEntity));
        }
        return result;
    }

    @Override
    public List<StudentDTORes> removeStudentByClassID(Long id, List<Long> ids) throws UnsupportedEncodingException {
        Optional<ClassEntity> data = classRepository.findById(id);
        if(!data.isPresent()){
            return null;
        }
        ClassEntity curr = data.get();
        for (Long stuId: ids){
            StudentEntity studentEntity = studentRepository.findById(stuId).get();
            curr.getStudentEntities().remove(studentEntity);
        }
        classRepository.save(curr);
        List<StudentDTORes> result = new ArrayList<>();
        for (StudentEntity studentEntity : curr.getStudentEntities()){
            result.add(studentConverterRes.toDTO(studentEntity));
        }
        return result;
    }

    @Override
    public List<EmployeeDTORes> removeEmployeeByClassID(Long id, List<Long> ids) throws UnsupportedEncodingException {
        Optional<ClassEntity> data = classRepository.findById(id);
        if(!data.isPresent()){
            return null;
        }
        ClassEntity curr = data.get();
        for (Long empId: ids){
            EmployeeEntity employeeEntity = employeeRepository.findById(empId).get();
            curr.getEmployeeEntities().remove(employeeEntity);
        }
        classRepository.save(curr);
        List<EmployeeDTORes> result = new ArrayList<>();
        for (EmployeeEntity employeeEntity : curr.getEmployeeEntities()){
            result.add(employeeConverterRes.toDTO(employeeEntity));
        }
        return result;
    }

    @Override
    public List<SubjectDTORes> removeSubjectByClassID(Long id, List<Long> ids) throws UnsupportedEncodingException {
        Optional<ClassEntity> data = classRepository.findById(id);
        if(!data.isPresent()){
            return null;
        }
        ClassEntity curr = data.get();
        for (Long subId: ids){
            SubjectEntity subjectEntity = subjectRepository.findById(subId).get();
            curr.getSubjectEntities().remove(subjectEntity);
        }
        classRepository.save(curr);
        List<SubjectDTORes> result = new ArrayList<>();
        for (SubjectEntity subjectEntity : curr.getSubjectEntities()){
            result.add(subjectConverterRes.toDTO(subjectEntity));
        }
        return result;
    }

    private List<StudentDTO> findStudentsByClassIDNative(Long id) {
        Optional<ClassEntity> optCur = classRepository.findById(id);
        if(!optCur.isPresent()) return new ArrayList<StudentDTO>();

        ClassEntity curr = optCur.get();
        List<StudentEntity> entityList = curr.getStudentEntities();
        if(entityList.size() == 0){
            return new ArrayList<StudentDTO>();
        }
        List<StudentDTO> result = new ArrayList<>();
        for (StudentEntity studentEntity : entityList){
            result.add(studentConverter.toDTO(studentEntity));
        }
        return result;
    }

    private List<EmployeeDTO> findAllEmployeeByClassIDNative(Long id) throws UnsupportedEncodingException {
        Optional<ClassEntity> optCur = classRepository.findById(id);
        if(!optCur.isPresent()) return new ArrayList<EmployeeDTO>();

        ClassEntity curr = optCur.get();
        List<EmployeeEntity> entityList = curr.getEmployeeEntities();
        if(entityList.size() == 0){
            return new ArrayList<EmployeeDTO>();
        }
        List<EmployeeDTO> result = new ArrayList<>();
        for (EmployeeEntity employeeEntity : entityList){
            result.add(employeeConverter.toDTO(employeeEntity));
        }
        return result;
    }

    private List<SubjectDTO> findAllSubjectByClassIDNative(Long id) {
        Optional<ClassEntity> optCur = classRepository.findById(id);
        if(!optCur.isPresent()) return new ArrayList<SubjectDTO>();

        ClassEntity curr = optCur.get();
        List<SubjectEntity> entityList = curr.getSubjectEntities();
        if(entityList.size() == 0){
            return new ArrayList<SubjectDTO>();
        }
        List<SubjectDTO> result = new ArrayList<>();
        for (SubjectEntity subjectEntity : entityList){
            result.add(subjectConverter.toDTO(subjectEntity));
        }
        return result;
    }
}
