package com.team.final8teamproject.owner.controller;

import com.team.final8teamproject.owner.dto.OwnerSignupRequestDto;
import com.team.final8teamproject.security.jwt.JwtUtil;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.user.dto.*;
import com.team.final8teamproject.owner.service.OwnerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owner")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public MessageResponseDto signup(@RequestBody @Valid OwnerSignupRequestDto ownersignupRequestDto) {
        if (!ownersignupRequestDto.getPassword().equals((ownersignupRequestDto.getPassword2()))) {
            throw new IllegalArgumentException("일치X");
        }
        return ownerService.signUp(ownersignupRequestDto);
    }

        //2.로그인
        @PostMapping("/login")
        public MessageResponseDto login (@RequestBody LoginRequestDto LoginRequestDto, HttpServletResponse response){
            //이름과 유저인지 관리자인지 구분한 토큰을 가져오는 부분
            LoginResponseDto msg = ownerService.login(LoginRequestDto);
            //문자열 token에 가져온 정보를 넣어주는 부분
            String token = msg.getAccessToken();
            //헤더를 통해 토큰을 발급해 주는 부분
            response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
            return new MessageResponseDto("로그인 되었습니다.");
        }

    //3. 로그아웃
    @DeleteMapping("/logout")
    public MessageResponseDto logout(@AuthenticationPrincipal UserDetailsImpl userDetails
            , HttpServletRequest request) {
        String accessToken = jwtUtil.resolveToken(request);
        return new MessageResponseDto(ownerService.logout(accessToken, userDetails.getUsername()));
    }

}
