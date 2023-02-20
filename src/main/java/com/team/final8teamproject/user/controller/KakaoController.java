package com.team.final8teamproject.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.team.final8teamproject.security.jwt.JwtUtil;
import com.team.final8teamproject.user.dto.KakaoUserInfoDto;
import com.team.final8teamproject.user.dto.LoginResponseDto;
import com.team.final8teamproject.user.service.KakaoService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class KakaoController {

    @Autowired
    private final KakaoService kakaoService;

    @GetMapping("/home")
    @ResponseBody
    public ResponseEntity<?> redirect() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://127.0.0.1:5500/src/main/resources/templates/index.html"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
//https://kauth.kakao.com/oauth/authorize?client_id=adacc39e0738d79ed04fd79995f809d3&redirect_uri=http://localhost:8080/api/user/kakao/callback&response_type=code
    //카카오
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response, KakaoUserInfoDto kakaoUserInfoDto) throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드

        LoginResponseDto createToken = kakaoService.kakaoLogin(code, response, kakaoUserInfoDto);

        String token = createToken.getAccessToken();


        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);

//        return new MessageResponseDto("로그인 되었습니다.");


        return "redirect:/api/home";
    }

}

