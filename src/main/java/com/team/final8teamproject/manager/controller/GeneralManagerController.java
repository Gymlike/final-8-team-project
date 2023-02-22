package com.team.final8teamproject.manager.controller;

import com.team.final8teamproject.manager.dto.*;
import com.team.final8teamproject.manager.entity.GeneralManager;
import com.team.final8teamproject.manager.service.GeneralManagerService;
import com.team.final8teamproject.security.jwt.JwtUtil;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.user.dto.LoginRequestDto;
import com.team.final8teamproject.user.dto.LoginResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/api/general")
@RequiredArgsConstructor
public class GeneralManagerController {
    private final GeneralManagerService generalManagerService;
    private final JwtUtil jwtUtil;
    //저장 (회원가입)
    @PostMapping("/signup")
    public ManagerMessageDto save(GeneralManager manager){
        generalManagerService.save(manager);
        return new ManagerMessageDto("총 관리자 생성에 성공하였습니다.");
    }

    //총관리자 로그인
    @PostMapping("/login")
    public ManagerMessageDto login(@RequestBody ManagerLoginRequestDto loginRequestDto, HttpServletResponse response) {
        //이름과 유저인지 관리자인지 구분한 토큰을 가져오는 부분
        LoginResponseDto msg = generalManagerService.login(loginRequestDto);
        //문자열 token에 가져온 정보를 넣어주는 부분
        String token = msg.getAccessToken();
        //헤더를 통해 토큰을 발급해 주는 부분
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        return new ManagerMessageDto("로그인 되었습니다.");
    }

    //승인
    @PutMapping("/approval")
    public ManagerMessageDto approval(ApprovalRequestDto request) {
        return generalManagerService.approval(request.getManager());
    }
//    @AuthenticationPrincipal UserDetailsImpl userDetails
    //승인대기 관리자 조회
    @GetMapping("/wait_approval")
    public List<WaitMangerResponseDto> waitManagerList(@PageableDefault Pageable pageable){
        return generalManagerService.waitManagerList(pageable);
    }
    //로그아웃
    @DeleteMapping("/logout")
    public ManagerMessageDto logout(@AuthenticationPrincipal UserDetailsImpl userDetails
            , HttpServletRequest request) {
        String accessToken = jwtUtil.resolveToken(request);
        generalManagerService.logout(accessToken, userDetails.getUsername());
        return new ManagerMessageDto("로그아웃 하셨습니다.");
    }

}
