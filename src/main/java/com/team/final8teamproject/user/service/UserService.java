package com.team.final8teamproject.user.service;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.base.repository.BaseRepository;
import com.team.final8teamproject.base.dto.BaseEntityProjectionDto;
import com.team.final8teamproject.redis.cache.CacheNames;
import com.team.final8teamproject.redis.RedisUtil;
import com.team.final8teamproject.security.service.EmailService;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import com.team.final8teamproject.user.dto.*;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import com.team.final8teamproject.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.team.final8teamproject.security.jwt.JwtUtil;

import java.util.*;

import org.slf4j.Logger;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BaseRepository baseRepository;
    private final RedisUtil redisUtil;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;
    private final Logger loggers = LoggerFactory.getLogger(UserService.class);
    private final RedisTemplate<String, String> redisTemplate;
    //1. 회원가입
    @CacheEvict(cacheNames = CacheNames.ALLUSERS,key = "'SimpleKey []'")
    @Transactional
    public MessageResponseDto signUp(@Valid SignupRequestDto signupRequestDto) {

        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String nickName = signupRequestDto.getNickName();
        String email = signupRequestDto.getEmail();
        String phoneNumber = signupRequestDto.getPhoneNumber();

        Optional<BaseEntity> findUserName = baseRepository.findByUsername(username);
        if (findUserName.isPresent()) {
            throw new CustomException(ExceptionStatus.DUPLICATED_USERNAME);
        }

        Optional<BaseEntity> findNickName = baseRepository.findByNickName(nickName);
        if (findNickName.isPresent()) {
            throw new CustomException(ExceptionStatus.DUPLICATED_NICKNAME);
        }

        Optional<BaseEntity> findEmail = baseRepository.findByEmail(email);
        if (findEmail.isPresent()) {
            throw new CustomException(ExceptionStatus.DUPLICATED_EMAIL);
        }

        Optional<User> findPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        if (findPhoneNumber.isPresent()) {
            throw new CustomException(ExceptionStatus.DUPLICATED_PHONENUMBER);
        }

        UserRoleEnum role = UserRoleEnum.MEMBER;
        User user = User.builder()
                .nickName(nickName).email(email)
                .phoneNumber(phoneNumber).password(password)
                .username(username).role(role)
                .build();
        userRepository.save(user);
        return new MessageResponseDto("회원가입 성공");
    }

    //2. 로그인
    @Cacheable(cacheNames = CacheNames.LOGINUSER, key = "'login'+#p0.getUsername()", unless = "#result == null")
    @Transactional
    public UserResponseDto login(LoginRequestDto loginRequestDto) {

        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();
        BaseEntityProjectionDto user =  baseRepository.findByUsernameAndInLiveIsTrue(username).orElseThrow(
                () -> new CustomException(ExceptionStatus.WRONG_USERNAME)
        );
//        BaseEntity user = baseRepository.findByUsername(username).orElseThrow(
//                () -> new CustomException(ExceptionStatus.WRONG_USERNAME)
//        );
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(ExceptionStatus.WRONG_USERNAME);
        }
        return UserResponseDto.of(user);
    }

    //두번째 파라미터는 username을 넣기 위해서 #p1을 넣어줌
    @CacheEvict(cacheNames = CacheNames.USERBYUSERNAME, key="'login'+#p1")
    //3. 로그아웃
    @Transactional
    public String logout(String accessToken, String username) {

        // 레디스에 accessToken 사용못하도록 등록
        Long expiration = jwtUtil.getExpiration(accessToken);
        redisUtil.setBlackList(accessToken, "logout", expiration);

        if (redisUtil.hasKey(username)) {
            redisUtil.deleteRefreshToken(username);
        } else {
            throw new IllegalArgumentException("이미 로그아웃한 유저입니다.");
        }
        return "로그아웃 완료";
    }

    //4. 유저 아이디 찾기
    @Transactional
    public FindByResponseDto findByUsername(String email) {
        BaseEntity user = baseRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("이메일을 다시 입력해주시기 바랍니다.")
        );
        if (user.getUsername().isEmpty()) {
            throw new NoSuchElementException("소셜 회원 가입자는 찾을수 없습니다.");
        }
        String username = user.getUsername();
        return new FindByResponseDto(username);
    }


    //5. 비밀번호찾기
    @Transactional
    public FindByResponseDto userFindPassword(FindPasswordRequestDto vo) {
        BaseEntity user = baseRepository.findByEmail(vo.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("이메일을 다시 입력해주시기 바랍니다.")
        );
        // 가입된 아이디가 없으면
        if (!user.isUsername(vo.getUsername())) {
            throw new IllegalArgumentException("등록되지 않은 사용자입니다.");
        }

        // 가입된 이메일이 아니면
        else if (!user.isEmail(vo.getEmail())) {
            throw new IllegalArgumentException("등록되지 않은 사용자입니다.");
        } else {

            // 임시 비밀번호 생성
            StringBuilder pw = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                pw.append((char) ((Math.random() * 26) + 97));
            }
            user.changePassword(passwordEncoder.encode(pw.toString()));
            // 비밀번호 변경 메일 발송
            emailService.sendPasswordEmail(vo, pw.toString());
        }
        return new FindByResponseDto("임시 패스워드 발송 성공");
    }



    public String getUserNickname(BaseEntity base) {
        String username = base.getUsername();
        BaseEntity user = baseRepository.findByUsername(username).orElseThrow(() -> new CustomException(ExceptionStatus.WRONG_USERNAME));
        return user.getNickName();
    }
}

