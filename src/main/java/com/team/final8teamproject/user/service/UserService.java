package com.team.final8teamproject.user.service;

import com.team.final8teamproject.security.redis.RedisUtil;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import com.team.final8teamproject.user.dto.*;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import com.team.final8teamproject.user.repository.RefreshTokenRepository;
import com.team.final8teamproject.user.repository.UserRepository;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.team.final8teamproject.security.jwt.JwtUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String MANAGER_TOKEN = "D1d@A$5dm4&4D1d1i34n%7";

    private final UserRepository userRepository;
    private final RedisUtil redisUtil;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    //1. 회원가입
    @Transactional
    public MessageResponseDto signUp(SignupRequestDto requestDto) {

        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
//        String password2 = passwordEncoder.encode(requestDto.getPassword2());
        String nickName = requestDto.getNickName();
        String email = requestDto.getEmail();
        String phoneNumber = requestDto.getPhoneNumber();

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        UserRoleEnum role = UserRoleEnum.OWNER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(MANAGER_TOKEN)) {
                throw new SecurityException("관리자 암호가 틀렸습니다.");
            }
            role = UserRoleEnum.MANAGER;
        }

        User user = User.builder()
                .username(username).password(password)
                .nickName(nickName).email(email).phoneNumber(phoneNumber)
                .role(role).experience(requestDto.getExperience())
                .build();
        userRepository.save(user);
        return new MessageResponseDto("회원가입 성공");
    }

    //2. 로그인
    @Transactional
    public LoginResponseDto login(LoginRequestDto requestDto) {

        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(ExceptionStatus.WRONG_USERNAME)
        );
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(ExceptionStatus.WRONG_PASSWORD);
        }
//        String refreshToken = (String)redisUtil.get("RT:" +user.getUsername());
//        if(!ObjectUtils.isEmpty(refreshToken)){
//            throw new IllegalArgumentException("이미 로그인 되어 있습니다..");
//        }
//        LoginResponseDto loginResponseDto =jwtUtil.createToken(user.getUsername(), user.getRole());
//        redisUtil.set("RT:" +user.getUsername(), loginResponseDto.getRefreshToken(), loginResponseDto.getRefreshTokenExpirationTime());

        return jwtUtil.createToken(user.getUsername(), user.getRole());
    }


    //3. 로그아웃
    public String logout(String accessToken, User users) {

        // refreshToken 테이블의 refreshToken 삭제
        redisUtil.delete("RT:" + users.getUsername());
//        refreshTokenRepository.deleteRefreshTokenByEmail(users.getEmail());

        // 레디스에 accessToken 사용못하도록 등록
        redisUtil.setBlackList("RT:" + accessToken, "accessToken", 5L);

        return "로그아웃 완료";
    }

}
