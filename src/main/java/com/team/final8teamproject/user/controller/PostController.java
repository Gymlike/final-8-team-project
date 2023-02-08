package com.team.final8teamproject.user.controller;

import com.team.final8teamproject.user.dto.MessageResponseDto;
import com.team.final8teamproject.user.dto.PostRequestDto;
import com.team.final8teamproject.user.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.security.jwt.JwtUtil;

@RestController
//@ResponseBody(데이터를 반환해야 하는 경우 Json 형태로 반환) + @Controller(Spring MVC의 컨트롤러, Model 객체를 만들어 데이터를 담고 View를 반환
@RequiredArgsConstructor //의존성주입으로 사용, final을 가지고 있는 속성에 롬복에의해 자동으로 생성자를 만들어줌
@RequestMapping("/api") // 중복 매핑
public class PostController {
    //jwtUtil에 comment어느테이션을 넣어준이유는
    //Service에서 사용하기 위해서이다.
    private final PostService postService;
    private final JwtUtil jwtUtil;

    //1. 게시글 생성 API
    @PostMapping("/post")
    public MessageResponseDto createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return postService.createPost(requestDto, userDetails.getUser());
    }
}