package com.example.backend.service;

import com.example.backend.dto.RoleDTO;
import com.example.backend.dtoresponse.AnnouncementDTORes;
import com.example.backend.dtoresponse.RoleDTORes;
import com.example.backend.model.RoleEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IRoleService {
    RoleDTORes findOne(Long id) throws UnsupportedEncodingException;

    RoleDTORes create(RoleDTO roleDTO) throws UnsupportedEncodingException;

    void delete(Long id);

    RoleDTORes update(RoleDTO roleDTO) throws UnsupportedEncodingException;

    List<RoleDTORes> findAll() throws UnsupportedEncodingException;

    List<AnnouncementDTORes> findAnnouncementsByRoleID(Long id) throws UnsupportedEncodingException;

    List<AnnouncementDTORes> addAnnouncementsByRoleID(Long id, List<Long> ids) throws UnsupportedEncodingException;
    List<AnnouncementDTORes> removeAnnouncementsByRoleID(Long id, List<Long> ids) throws UnsupportedEncodingException;
}
