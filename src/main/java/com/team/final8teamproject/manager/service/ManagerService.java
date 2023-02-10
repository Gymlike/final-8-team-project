package com.team.final8teamproject.manager.service;

import com.team.final8teamproject.manager.dto.ManagerLoginRequestDto;
import com.team.final8teamproject.manager.dto.ManagerLoginResponseDto;
import com.team.final8teamproject.manager.dto.ManagerSignupRequestDto;
import com.team.final8teamproject.manager.dto.ManagerSignupResponseDto;

public interface ManagerService {
    ManagerSignupResponseDto signup(ManagerSignupRequestDto managerSignupRequestDto);
    ManagerLoginResponseDto login(ManagerLoginRequestDto managerLoginRequestDto);
    void logout(String accessToken, String manager);

}
