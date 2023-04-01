package com.team.final8teamproject.security.service;

import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.user.dto.FindPasswordRequestDto;

import java.time.LocalDateTime;

public interface EmailService {
    void sendSimpleMessage(String to) throws Exception;

    boolean verifyAuthCode(String to, String authCode) throws CustomException;

    LocalDateTime getAuthCodeCreatedAt(String to);

    void sendEmail(FindPasswordRequestDto vo, String password);
}
