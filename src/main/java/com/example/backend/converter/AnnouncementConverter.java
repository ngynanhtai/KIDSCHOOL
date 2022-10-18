package com.example.backend.converter;

import com.example.backend.dto.AnnouncementDTO;
import com.example.backend.model.AnnouncementEntity;
import com.example.backend.model.RoleEntity;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnnouncementConverter {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private RoleRepository roleRepository;

    public AnnouncementDTO toDTO(AnnouncementEntity entity) {
        AnnouncementDTO result = new AnnouncementDTO();
        List<Long> roleId_List = new ArrayList<>();
        result.setId(entity.getId());
        result.setCreateAt(entity.getCreateAt());
        result.setContent(entity.getContent());
        result.setTitle(entity.getTitle());
        if (entity.getEmployeeEntity() != null) {
            result.setEmp_id(entity.getEmployeeEntity().getId());
        }
        if (entity.getSchoolEntity() != null) {
            result.setSchool_id(entity.getSchoolEntity().getId());
        }
        for (RoleEntity item : entity.getRoleEntities()) {
            roleId_List.add(item.getId());
        }
        result.setRoleId_list(roleId_List);
        return result;
    }

    public AnnouncementEntity toEntity(AnnouncementDTO dto) {
        AnnouncementEntity result = new AnnouncementEntity();
        List<RoleEntity> roleEntityList = new ArrayList<>();
        result.setId(dto.getId() != null ? dto.getId() : 0);
        result.setCreateAt(dto.getCreateAt() != null ? dto.getCreateAt() : null);
        result.setContent(dto.getContent());
        result.setTitle(dto.getTitle());
        if (dto.getEmp_id() != null) {
            result.setEmployeeEntity(employeeRepository.findById(dto.getEmp_id()).get());
        }
        if (dto.getSchool_id() != null) {
            result.setSchoolEntity(schoolRepository.findById(dto.getSchool_id()).get());
        }
        if(dto.getRoleId_list().size() > 0){
            for (Long item : dto.getRoleId_list()) {
                roleEntityList.add(roleRepository.findById(item).get());
            }
        }
        result.setRoleEntities(roleEntityList);
        return result;
    }

    public AnnouncementEntity toEntity(AnnouncementEntity result, AnnouncementDTO dto) {
        List<RoleEntity> roleEntityList = new ArrayList<>();
        result.setCreateAt(dto.getCreateAt());
        result.setContent(dto.getContent());
        result.setTitle(dto.getTitle());
        if (dto.getEmp_id() != null) {
            result.setEmployeeEntity(employeeRepository.findById(dto.getEmp_id()).get());
        }
        if (dto.getSchool_id() != null) {
            result.setSchoolEntity(schoolRepository.findById(dto.getSchool_id()).get());
        }
        for (Long item : dto.getRoleId_list()) {
            roleEntityList.add(roleRepository.findById(item).get());
        }
        result.setRoleEntities(roleEntityList);
        return result;
    }
}
