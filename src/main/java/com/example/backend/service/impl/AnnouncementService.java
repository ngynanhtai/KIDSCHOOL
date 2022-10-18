package com.example.backend.service.impl;

import com.example.backend.converter.AnnouncementConverter;
import com.example.backend.converter.RoleConverter;
import com.example.backend.convertresponse.AnnouncementConverterRes;
import com.example.backend.convertresponse.RoleConverterRes;
import com.example.backend.dto.AnnouncementDTO;
import com.example.backend.dto.RoleDTO;
import com.example.backend.dtoresponse.AnnouncementDTORes;
import com.example.backend.dtoresponse.RoleDTORes;
import com.example.backend.model.AnnouncementEntity;
import com.example.backend.model.RoleEntity;
import com.example.backend.repository.AnnouncementRepository;
import com.example.backend.repository.RoleRepository;
import com.example.backend.service.IAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService implements IAnnouncementService {

    private AnnouncementEntity entity;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private AnnouncementConverter announcementConverter;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private AnnouncementConverterRes announcementConverterRes;

    @Autowired
    private RoleConverterRes roleConverterRes;

    @Override
    public AnnouncementDTORes findOne(Long id) throws UnsupportedEncodingException {
        Optional<AnnouncementEntity> data = announcementRepository.findById(id);
        return data.isPresent()
              ? announcementConverterRes.toDTO(data.get())
              : null;
    }

    @Override
    public AnnouncementDTORes create(AnnouncementDTO announcementDTO) throws UnsupportedEncodingException {
        entity = announcementConverter.toEntity(announcementDTO);
        entity.setCreateAt(new Date().toString());
        return announcementConverterRes.toDTO(announcementRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        announcementRepository.deleteById(id);
    }
    @Override
    public AnnouncementDTORes update(AnnouncementDTO announcementDTO) throws UnsupportedEncodingException {
        AnnouncementEntity currEntity = announcementRepository.findById(announcementDTO.getId()).get();
        entity = announcementConverter.toEntity(currEntity, announcementDTO);
        return announcementConverterRes.toDTO(announcementRepository.save(entity));
    }

    @Override
    public List<AnnouncementDTORes> findAll() throws UnsupportedEncodingException {
        List<AnnouncementDTORes> result = new ArrayList<>();
        List<AnnouncementEntity> entityList = announcementRepository.findAll();
        for (AnnouncementEntity item : entityList) {
            result.add(announcementConverterRes.toDTO(item));
        }
        return result;
    }

    @Override
    public List<RoleDTORes> addRoleForAnnouncement(Long id, List<Long> ids) throws UnsupportedEncodingException {
        Optional<AnnouncementEntity> announcementEntity = announcementRepository.findById(id);
        if(!announcementEntity.isPresent()) {
            return null;
        }
        AnnouncementEntity curr = announcementEntity.get();
        for (Long roleId : ids){
            curr.getRoleEntities().add(roleRepository.findById(roleId).get());
        }
        announcementRepository.save(curr);
        List<RoleDTORes> result = new ArrayList<>();
        for (RoleEntity roleEntity : curr.getRoleEntities()){
            result.add(roleConverterRes.toDTO(roleEntity));
        }
        return result;
    }

    @Override
    public List<RoleDTORes> removeRoleForAnnouncement(Long id, List<Long> ids) throws UnsupportedEncodingException {
        Optional<AnnouncementEntity> announcementEntity = announcementRepository.findById(id);
        if(!announcementEntity.isPresent()) {
            return null;
        }
        AnnouncementEntity curr = announcementEntity.get();
        for (Long roleId : ids){
            RoleEntity roleEntity = roleRepository.findById(roleId).get();
            curr.getRoleEntities().remove(roleEntity);
        }
        announcementRepository.save(curr);
        List<RoleDTORes> result = new ArrayList<>();
        for (RoleEntity roleEntity : curr.getRoleEntities()){
            result.add(roleConverterRes.toDTO(roleEntity));
        }
        return result;
    }

    @Override
    public List<RoleDTORes> getRolesByAnnouncementID(Long id) throws UnsupportedEncodingException {
        Optional<AnnouncementEntity> announcementEntity = announcementRepository.findById(id);
        if(!announcementEntity.isPresent()){
            return null;
        }
        AnnouncementEntity curr = announcementEntity.get();
        if(curr.getRoleEntities().size() == 0){
            return new ArrayList<>();
        }
        List<RoleDTORes> result = new ArrayList<>();
        for (RoleEntity roleEntity : curr.getRoleEntities()){
            result.add(roleConverterRes.toDTO(roleEntity));
        }
        return result;
    }
}
