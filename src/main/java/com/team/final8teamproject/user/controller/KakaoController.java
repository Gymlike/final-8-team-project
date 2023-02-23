package com.team.final8teamproject.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.team.final8teamproject.security.jwt.JwtUtil;
import com.team.final8teamproject.user.dto.KakaoUserInfoDto;
import com.team.final8teamproject.user.dto.LoginResponseDto;
import com.team.final8teamproject.user.service.KakaoService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class KakaoController {

    @Autowired
    private final KakaoService kakaoService;

//https://kauth.kakao.com/oauth/authorize?client_id=adacc39e0738d79ed04fd79995f809d3&redirect_uri=http://localhost:5500/api/user/kakao/callback&response_type=code

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드

        LoginResponseDto createToken = kakaoService.kakaoLogin(code, response);

        String token = createToken.getAccessToken();

        response.setHeader("Authorization", token);

        return "redirect:http://127.0.0.1:5501/callback.html?token="+ token;
    }

}

