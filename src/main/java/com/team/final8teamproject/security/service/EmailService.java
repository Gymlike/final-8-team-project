package com.team.final8teamproject.security.service;

import com.team.final8teamproject.share.exception.CustomException;

public interface EmailService {

    void sendSimpleMessage(String to) throws CustomException, Exception;

}
