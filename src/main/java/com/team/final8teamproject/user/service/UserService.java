package com.team.final8teamproject.user.service;

import com.team.final8teamproject.security.redis.RedisUtil;
import com.team.final8teamproject.user.dto.LoginRequestDto;
import com.team.final8teamproject.user.dto.LoginResponseDto;
import com.team.final8teamproject.user.dto.MessageResponseDto;
import com.team.final8teamproject.user.dto.SignupRequestDto;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import com.team.final8teamproject.user.repository.RefreshTokenRepository;
import com.team.final8teamproject.user.repository.UserRepository;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.team.final8teamproject.security.jwt.JwtUtil;

import java.util.Optional;

@Slf4j
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
        String phoneNumber =requestDto.getPhoneNumber();
        Long experience = requestDto.getExperience();

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

//        UserRoleEnum role = UserRoleEnum.OWNER;
//        if (requestDto.isAdmin()) {
//            if (!requestDto.getAdminToken().equals(MANAGER_TOKEN)) {
//                throw new SecurityException("관리자 암호가 틀렸습니다.");
//            }
//            role = UserRoleEnum.MANAGER;
//        }

        UserRoleEnum role = UserRoleEnum.MEMBER;
        User user = User.builder()
                .nickName(nickName).email(email)
                .phoneNumber(phoneNumber).password(password)
                .username(username).role(role).experience(experience)
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
                () -> new SecurityException("사용자를 찾을수 없습니다.")
        );
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new SecurityException("사용자를 찾을수 없습니다.");
        }
        LoginResponseDto loginResponseDto =jwtUtil.createToken(user.getUsername(), user.getRole());

        if(redisUtil.hasKey("RT:" +user.getUsername())){
            throw new SecurityException("이미 접속중인 사용자 입니다.");
        }
        redisUtil.setRefreshToken("RT:" +user.getUsername(), loginResponseDto.getRefreshToken(), loginResponseDto.getRefreshTokenExpirationTime());

        return loginResponseDto;
    }

    //3. 로그아웃
    @Transactional
    public String logout(String accessToken, String username) {

        // 레디스에 accessToken 사용못하도록 등록
        Long expiration = jwtUtil.getExpiration(accessToken);
        redisUtil.setBlackList(accessToken, "logout", expiration);

        if(redisUtil.hasKey("RT:" + username)){
            redisUtil.deleteRefreshToken("RT:" + username);
        } else {
            throw new IllegalArgumentException("이미 로그아웃한 유저입니다.");
        }
        return "로그아웃 완료";
    }


}
