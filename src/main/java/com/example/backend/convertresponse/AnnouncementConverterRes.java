package com.example.backend.convertresponse;

import com.example.backend.converter.AnnouncementConverter;
import com.example.backend.converter.EmployeeConverter;
import com.example.backend.converter.RoleConverter;
import com.example.backend.converter.SchoolConverter;
import com.example.backend.dto.AnnouncementDTO;
import com.example.backend.dto.RoleDTO;
import com.example.backend.dtoresponse.AnnouncementDTORes;
import com.example.backend.model.AnnouncementEntity;
import com.example.backend.model.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnnouncementConverterRes {

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private SchoolConverter schoolConverter;

    @Autowired
    private RoleConverter roleConverter;

    public AnnouncementDTORes toDTO(AnnouncementEntity announcementEntity) throws UnsupportedEncodingException {
        AnnouncementDTORes announcementDTORes = new AnnouncementDTORes();
        announcementDTORes.setId(announcementEntity.getId());
        announcementDTORes.setTitle(announcementEntity.getTitle());
        announcementDTORes.setContent(announcementEntity.getContent());
        announcementDTORes.setCreateAt(announcementEntity.getCreateAt());

        announcementDTORes.setEmployeeDTO(announcementEntity.getEmployeeEntity() == null ? null : employeeConverter.toDTO(announcementEntity.getEmployeeEntity()));
        announcementDTORes.setSchoolDTO(announcementEntity.getSchoolEntity() == null ? null : schoolConverter.toDTO(announcementEntity.getSchoolEntity()));

        List<RoleDTO> roleDTOS = new ArrayList<>();
        if(announcementEntity.getRoleEntities() != null && announcementEntity.getRoleEntities().size() > 0){
            for (RoleEntity roleEntity : announcementEntity.getRoleEntities()){
                roleDTOS.add(roleConverter.toDTO(roleEntity));
            }
        }
        announcementDTORes.setRoleDTOS(roleDTOS);
        return announcementDTORes;
    }
}
