package com.example.backend.service;

import com.example.backend.dto.etc.EmailDTO;

public interface IEmailService {
    Boolean sendSimpleMail(EmailDTO details);
    Boolean sendMailWithAttachment(EmailDTO details);
}
