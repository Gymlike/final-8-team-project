package com.team.final8teamproject.owner.controller;

import com.team.final8teamproject.owner.dto.OwnerLoginRequestDto;
import com.team.final8teamproject.owner.dto.OwnerSignupRequestDto;
import com.team.final8teamproject.security.jwt.JwtUtil;
import com.team.final8teamproject.security.ownerservice.OwnerDetailsImpl;
import com.team.final8teamproject.user.dto.*;
import com.team.final8teamproject.owner.service.OwnerService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;
    @PostMapping("/signup")
    public MessageResponseDto signup(@RequestBody @Valid OwnerSignupRequestDto ownersignupRequestDto) {
        return ownerService.signUp(ownersignupRequestDto);
    }
    //2.로그인
    @PostMapping("/login")
    public MessageResponseDto login(@RequestBody OwnerLoginRequestDto ownerLoginRequestDto, HttpServletResponse response) {
        //이름과 유저인지 관리자인지 구분한 토큰을 가져오는 부분
        LoginResponseDto msg = ownerService.login(ownerLoginRequestDto);
        //문자열 token에 가져온 정보를 넣어주는 부분
        String token = msg.getAccessToken();
        //헤더를 통해 토큰을 발급해 주는 부분
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        return new MessageResponseDto("로그인 되었습니다.");
    }
    @DeleteMapping("/logout")
    public MessageResponseDto logout(@AuthenticationPrincipal OwnerDetailsImpl ownerDetails
            , @RequestBody TokenRequestDto tokenRequestDto){
        return new MessageResponseDto(ownerService.logout(tokenRequestDto.getAccessToken(), ownerDetails.getOwner()));
    }
}
