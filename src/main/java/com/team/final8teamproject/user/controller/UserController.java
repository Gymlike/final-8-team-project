package com.team.final8teamproject.user.controller;

import com.team.final8teamproject.aop.Timer;
import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.base.repository.BaseRepository;
import com.team.final8teamproject.security.redis.RedisUtil;
import com.team.final8teamproject.security.service.EmailService;
import com.team.final8teamproject.security.service.EmailServiceImpl;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.user.dto.*;
import com.team.final8teamproject.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.team.final8teamproject.security.jwt.JwtUtil;
import com.team.final8teamproject.security.service.UserDetailsImpl;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    EmailService emailService;

    @Autowired
    EmailServiceImpl emailServiceImpl;

    private final UserService userService;
    private final BaseRepository baseRepository;
    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //1. 회원가입
    @PostMapping("/signup")
    public MessageResponseDto signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        if (!signupRequestDto.getPassword().equals((signupRequestDto.getPassword2()))) {
            throw new IllegalArgumentException("일치X");
        }
        return userService.signUp(signupRequestDto);
    }

    //2.로그인
    @Timer
    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        //이름과 유저인지 관리자인지 구분한 토큰을 가져오는 부분
        UserResponseDto user = userService.login(loginRequestDto);
        return jwtUtil.createUserToken(user.getUsername(), user.getRole());
    }

    //3. 로그아웃
    @DeleteMapping("/logout")
    public MessageResponseDto logout(@AuthenticationPrincipal UserDetailsImpl userDetails
            , HttpServletRequest request) {
        String accessToken = jwtUtil.resolveToken(request);
        return new MessageResponseDto(userService.logout(accessToken, userDetails.getUsername()));
    }

    //4. 이메일 인증
    //이메일 전송
    @PostMapping("/email")
    @ResponseBody
    public void emailConfirm(String email) throws Exception {
        logger.info("post emailConfirm");
        Optional<BaseEntity> findEmail = baseRepository.findByEmail(email);
        if (findEmail.isPresent()) {
            throw new IllegalArgumentException("이메일 중복");
        } else emailService.sendSimpleMessage(email);
    }

    //이메일 코드 확인
    @PostMapping("/verifyCode")
    @ResponseBody
    public int verifyCode(String code, String email) throws CustomException {
        logger.info("Post verifyCode");
        int result = 0;
        System.out.println("인증실패");
        System.out.println("code : " + code);
        System.out.println("email : " + email);
        if (emailServiceImpl.verifyAuthCode(email, code)) {
            System.out.println("인증성공");
            result = 1;
        }
        return result;
    }

    //파일서비스란 인터페이스를 만들어서 이것을 이용하여 저장하게 해놓으면
    //나중에 할수있다.
    //http표준 요청상에는 get은 body를 사용하면 안된다. param을 사용해야한다.

    //5.ID 찾기
    @GetMapping("/find/username")
    public FindByResponseDto getfindByUsername(@RequestParam("email") String email) {
        return userService.findByUsername(email);
    }

    //6.Password 재발급
    @PostMapping("/find/password")
    public FindByResponseDto reissuancePassword(@RequestBody FindPasswordRequestDto requestDto) {
        return userService.userFindPassword(requestDto);
    }
    //7. 토큰 재발급(클라이언트에서 Access_Token이 만료될시)
    @PostMapping("/token/regenerate")
    @Timer
    public TokenResponseDto regenerateToken(@AuthenticationPrincipal UserDetailsImpl userDetails){
        UserResponseDto user = UserResponseDto.of(userDetails.getBase());
        return jwtUtil.createUserToken(user.getUsername(), user.getRole());
    }
}
