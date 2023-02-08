package com.team.final8teamproject.manager.service;

import com.team.final8teamproject.manager.dto.ManagerLoginRequestDto;
import com.team.final8teamproject.manager.dto.ManagerLoginResponseDto;
import com.team.final8teamproject.manager.dto.ManagerSignupRequestDto;
import com.team.final8teamproject.manager.dto.ManagerSignupReseponseDto;

public interface ManagerService {
    public ManagerSignupReseponseDto signup(ManagerSignupRequestDto managerSignupRequestDto);
    public ManagerLoginResponseDto login(ManagerLoginRequestDto managerLoginRequestDto);
    public void logout();

}
