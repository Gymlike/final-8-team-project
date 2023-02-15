package com.team.final8teamproject.manager.service;

import com.team.final8teamproject.manager.dto.ManagerLoginRequestDto;
import com.team.final8teamproject.manager.dto.ManagerLoginResponseDto;
import com.team.final8teamproject.manager.dto.ManagerSignupRequestDto;
import com.team.final8teamproject.manager.dto.ManagerSignupResponseDto;
import com.team.final8teamproject.user.dto.LoginResponseDto;

public interface ManagerService {
    ManagerSignupResponseDto signup(ManagerSignupRequestDto managerSignupRequestDto);
    LoginResponseDto login(ManagerLoginRequestDto managerLoginRequestDto);
    void logout(String accessToken, String manager);

}
