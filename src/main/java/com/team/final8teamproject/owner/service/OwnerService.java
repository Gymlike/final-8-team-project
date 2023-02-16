package com.team.final8teamproject.owner.service;

import com.team.final8teamproject.owner.dto.OwnerLoginRequestDto;
import com.team.final8teamproject.owner.dto.OwnerSignupRequestDto;
import com.team.final8teamproject.owner.entity.Owner;
import com.team.final8teamproject.security.jwt.JwtUtil;
import com.team.final8teamproject.security.redis.RedisUtil;
import com.team.final8teamproject.user.dto.*;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import com.team.final8teamproject.owner.repository.OwnerRepository;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OwnerService {
    private static final String MANAGER_TOKEN = "D1d@A$5dm4&4D1d1i34n%7";
    private final RedisUtil redisUtil;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final OwnerRepository ownerRepository;
    public MessageResponseDto signUp(OwnerSignupRequestDto requestDto) {
        String ownername = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickName = requestDto.getNickName();
        String email = requestDto.getEmail();
        String phoneNumber =requestDto.getPhoneNumber();
        String storeName = requestDto.getStoreName();
        String storeNumber = requestDto.getStoreNumber();

        Optional<Owner> found = ownerRepository.findByUsername(ownername);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        UserRoleEnum role = UserRoleEnum.OWNER;//내일 물어보기
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(MANAGER_TOKEN)) {
                throw new SecurityException("관리자 암호가 틀렸습니다.");
            }
            role = UserRoleEnum.MANAGER;
        }
        Owner owner = Owner.builder()
                .nickName(nickName).email(email)
                .phoneNumber(phoneNumber).password(password)
                .username(ownername).role(role)
                .storeName(storeName).ownerNumber(storeNumber)
                .build();
        ownerRepository.save(owner);
        return new MessageResponseDto("회원가입 성공");
    }
    //2.로그인
    @Transactional
    public LoginResponseDto login(OwnerLoginRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        Owner owner = ownerRepository.findByUsername(username).orElseThrow(
                () -> new SecurityException("사용자를 찾을수 없습니다.")
        );
        if (!passwordEncoder.matches(password, owner.getPassword())){
            throw new SecurityException("사용자를 찾을수 없습니다.");
        }
        String refreshToken = (String)redisUtil.getRefreshToken("RT:" +owner.getUsername());
        if(!ObjectUtils.isEmpty(refreshToken)){
            throw new IllegalArgumentException("이미 로그인 되어 있습니다..");
        }
        LoginResponseDto loginResponseDto =jwtUtil.createUserToken(owner.getUsername(), owner.getRole());

        redisUtil.setRefreshToken("RT:" +owner.getUsername(), loginResponseDto.getRefreshToken(), loginResponseDto.getRefreshTokenExpirationTime());

        return loginResponseDto;
    }

    public String logout(String accessToken, String username) {

        // refreshToken 테이블의 refreshToken 삭제
        redisUtil.deleteRefreshToken("RT:" + username);
//        refreshTokenRepository.deleteRefreshTokenByEmail(users.getEmail());

        // 레디스에 accessToken 사용못하도록 등록
        redisUtil.setBlackList("RT:"+accessToken, "accessToken", 5L);

        return "로그아웃 완료";
    }
}
