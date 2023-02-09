package com.team.final8teamproject.manager.controller;

import com.team.final8teamproject.manager.dto.ApprovalRequestDto;
import com.team.final8teamproject.manager.dto.ManagerMessageDto;
import com.team.final8teamproject.manager.dto.WaitMangerResponseDto;
import com.team.final8teamproject.manager.entity.GeneralManager;
import com.team.final8teamproject.manager.service.GeneralManagerService;
import com.team.final8teamproject.security.jwt.JwtUtil;
import com.team.final8teamproject.security.userservice.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
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
    //저장
    @PostMapping("/signup")
    public ManagerMessageDto save(GeneralManager manager){
        generalManagerService.save(manager);
        return new ManagerMessageDto("총 관리자 생성에 성공하였습니다.");
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
