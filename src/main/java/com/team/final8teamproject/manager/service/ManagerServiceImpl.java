package com.team.final8teamproject.manager.service;

import com.team.final8teamproject.manager.dto.ManagerLoginRequestDto;
import com.team.final8teamproject.manager.dto.ManagerSignupRequestDto;
import com.team.final8teamproject.manager.dto.ManagerSignupResponseDto;
import com.team.final8teamproject.manager.entity.Manager;
import com.team.final8teamproject.manager.repository.ManagerRepository;
import com.team.final8teamproject.security.jwt.JwtUtil;
import com.team.final8teamproject.security.redis.RedisUtil;
import com.team.final8teamproject.user.dto.LoginResponseDto;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import com.team.final8teamproject.user.repository.RefreshTokenRepository;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final RedisUtil redisUtil;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    //관리자 가입신청
    @Override
    @Transactional
    public ManagerSignupResponseDto signup(ManagerSignupRequestDto requestDto){
        String managerName = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickName = requestDto.getNickname();

        Optional<Manager> found = managerRepository.findByUsername(managerName);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        UserRoleEnum role = UserRoleEnum.WAIT;
        Manager manager = Manager.builder()
                .username(managerName).password(password)
                .nickname(nickName).role(role)
                .build();
        managerRepository.save(manager);
        return new ManagerSignupResponseDto("신청에 성공했습니다.");
    }

    // 관리자 로그인
    @Override
    @Transactional

    public LoginResponseDto login(ManagerLoginRequestDto requestDto){

        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        Manager manager = managerRepository.findByUsername(username).orElseThrow(
                () -> new SecurityException("사용자를 찾을수 없습니다.")
        );
        if (!passwordEncoder.matches(password, manager.getPassword())) {
            throw new SecurityException("사용자를 찾을수 없습니다.");
        }
        LoginResponseDto loginResponseDto =jwtUtil.createManagerToken(manager.getUsername(), manager.getRole());

        if(redisUtil.hasKey("RT:" +manager.getUsername())){
            throw new SecurityException("이미 접속중인 사용자 입니다.");
        }
        redisUtil.setRefreshToken("RT:" +manager.getUsername(), loginResponseDto.getRefreshToken(), loginResponseDto.getRefreshTokenExpirationTime());

        return loginResponseDto;
    }

    //관리자 로그아웃
    @Override
    @Transactional
    public void logout(String accessToken, String manager){
        if(redisUtil.getRefreshToken(accessToken) != null){
            redisUtil.deleteRefreshToken(manager);
        }
        // 레디스에 accessToken 사용못하도록 등록
        Long expiration = jwtUtil.getExpiration(accessToken);
        redisUtil.setBlackList(accessToken, "logout", expiration);
    }
}
