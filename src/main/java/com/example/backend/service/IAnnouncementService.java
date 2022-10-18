package com.example.backend.service;

import com.example.backend.dto.AnnouncementDTO;
import com.example.backend.dto.RoleDTO;
import com.example.backend.dtoresponse.AnnouncementDTORes;
import com.example.backend.dtoresponse.RoleDTORes;
import com.example.backend.model.AnnouncementEntity;
import com.example.backend.model.utils.GenericResponse;
import com.mysql.cj.log.Log;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IAnnouncementService {
    AnnouncementDTORes findOne(Long id) throws UnsupportedEncodingException;

    AnnouncementDTORes create(AnnouncementDTO announcementDTO) throws UnsupportedEncodingException;

    void delete(Long id);

    AnnouncementDTORes update(AnnouncementDTO announcementDTO) throws UnsupportedEncodingException;

    List<AnnouncementDTORes> findAll() throws UnsupportedEncodingException;

    List<RoleDTORes> addRoleForAnnouncement(Long id, List<Long> ids) throws UnsupportedEncodingException;

    List<RoleDTORes> removeRoleForAnnouncement(Long id, List<Long> ids) throws UnsupportedEncodingException;

    List<RoleDTORes> getRolesByAnnouncementID(Long id) throws UnsupportedEncodingException;

}
