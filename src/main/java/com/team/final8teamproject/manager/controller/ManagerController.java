package com.team.final8teamproject.manager.controller;

import com.team.final8teamproject.manager.dto.ManagerLoginRequestDto;
import com.team.final8teamproject.manager.dto.ManagerLoginResponseDto;
import com.team.final8teamproject.manager.dto.ManagerSignupRequestDto;
import com.team.final8teamproject.manager.dto.ManagerSignupResponseDto;
import com.team.final8teamproject.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/signup")
    public ManagerSignupResponseDto signup(ManagerSignupRequestDto requestDto){
        return managerService.signup(requestDto);
    }

    @PostMapping("/login")
    public ManagerLoginResponseDto login(ManagerLoginRequestDto requestDto){
        return managerService.login(requestDto);
    }
}
