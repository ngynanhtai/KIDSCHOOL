package com.example.backend.service.impl;

import com.example.backend.converter.RoleConverter;
import com.example.backend.convertresponse.AnnouncementConverterRes;
import com.example.backend.convertresponse.RoleConverterRes;
import com.example.backend.dto.RoleDTO;
import com.example.backend.dtoresponse.AnnouncementDTORes;
import com.example.backend.dtoresponse.RoleDTORes;
import com.example.backend.model.AnnouncementEntity;
import com.example.backend.model.RoleEntity;
import com.example.backend.repository.AnnouncementRepository;
import com.example.backend.repository.RoleRepository;
import com.example.backend.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private RoleConverterRes roleConverterRes;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private AnnouncementConverterRes announcementConverterRes;

    @Override
    public RoleDTORes findOne(Long id) throws UnsupportedEncodingException {
        Optional<RoleEntity> data = roleRepository.findById(id);
        return data.isPresent()
                ? roleConverterRes.toDTO(data.get())
                : null;
    }

    @Override
    public RoleDTORes create(RoleDTO roleDTO) throws UnsupportedEncodingException {
        return roleConverterRes.toDTO(roleRepository.save(roleConverter.toEntity(roleDTO)));
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public RoleDTORes update(RoleDTO roleDTO) throws UnsupportedEncodingException {
        RoleEntity curr = roleRepository.findById(roleDTO.getId()).get();
        RoleEntity entity = roleConverter.toEntity(curr, roleDTO);
        return roleConverterRes.toDTO(roleRepository.save(entity));
    }

    @Transactional
    @Override
    public List<RoleDTORes> findAll() throws UnsupportedEncodingException {
        List<RoleDTORes> result = new ArrayList<>();
        List<RoleEntity> entityList = roleRepository.findAll();
        for(RoleEntity roleEntity : entityList){
            result.add(roleConverterRes.toDTO(roleEntity));
        }

        return result;
    }

    @Override
    public List<AnnouncementDTORes> findAnnouncementsByRoleID(Long id) throws UnsupportedEncodingException {
        Optional<RoleEntity> roleEntity = roleRepository.findById(id);
        if(!roleEntity.isPresent()){
            return null;
        }
        RoleEntity curr = roleEntity.get();
        if(curr.getAnnouncementEntities().size() == 0){
            return new ArrayList<>();
        }
        List<AnnouncementDTORes> result = new ArrayList<>();
        for (AnnouncementEntity announcementEntity : curr.getAnnouncementEntities()){
            result.add(announcementConverterRes.toDTO(announcementEntity));
        }
        return result;
    }

    @Override
    public List<AnnouncementDTORes> addAnnouncementsByRoleID(Long id, List<Long> ids) throws UnsupportedEncodingException {
        Optional<RoleEntity> roleEntity = roleRepository.findById(id);
        if(!roleEntity.isPresent()){
            return null;
        }
        RoleEntity curr = roleEntity.get();
        for (Long announceID: ids){
            AnnouncementEntity announcementEntity = announcementRepository.findById(announceID).get();
            announcementEntity.getRoleEntities().add(curr);
            announcementRepository.save(announcementEntity);
        }
        RoleEntity data = roleRepository.findById(id).get();
        List<AnnouncementDTORes> result = new ArrayList<>();
        for (AnnouncementEntity announcementEntity : data.getAnnouncementEntities()){
            result.add(announcementConverterRes.toDTO(announcementEntity));
        }
        return result;
    }

    @Override
    public List<AnnouncementDTORes> removeAnnouncementsByRoleID(Long id, List<Long> ids) throws UnsupportedEncodingException {
        Optional<RoleEntity> roleEntity = roleRepository.findById(id);
        if(!roleEntity.isPresent()){
            return null;
        }
        RoleEntity curr = roleEntity.get();
        for (Long announceID: ids){
            AnnouncementEntity announcementEntity = announcementRepository.findById(announceID).get();
            announcementEntity.getRoleEntities().remove(curr);
            announcementRepository.save(announcementEntity);
        }
        RoleEntity data = roleRepository.findById(id).get();
        List<AnnouncementDTORes> result = new ArrayList<>();
        for (AnnouncementEntity announcementEntity : data.getAnnouncementEntities()){
            result.add(announcementConverterRes.toDTO(announcementEntity));
        }
        return result;
    }

}
