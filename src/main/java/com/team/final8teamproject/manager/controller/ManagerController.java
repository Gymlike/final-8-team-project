package com.team.final8teamproject.manager.controller;

import com.team.final8teamproject.manager.dto.*;
import com.team.final8teamproject.manager.service.ManagerService;
import com.team.final8teamproject.security.jwt.JwtUtil;
import com.team.final8teamproject.user.dto.LoginResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ManagerMessageDto login(@RequestBody ManagerLoginRequestDto requestDto, HttpServletResponse response){
        LoginResponseDto msg = managerService.login(requestDto);
        //문자열 token에 가져온 정보를 넣어주는 부분
        String token = msg.getAccessToken();
        //헤더를 통해 토큰을 발급해 주는 부분
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        return new ManagerMessageDto("로그인 되었습니다.");
    }
}