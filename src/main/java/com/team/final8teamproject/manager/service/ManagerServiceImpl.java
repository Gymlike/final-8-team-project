package com.team.final8teamproject.manager.service;

import com.team.final8teamproject.manager.dto.ManagerLoginRequestDto;
import com.team.final8teamproject.manager.dto.ManagerLoginResponseDto;
import com.team.final8teamproject.manager.dto.ManagerSignupRequestDto;
import com.team.final8teamproject.manager.dto.ManagerSignupResponseDto;
import com.team.final8teamproject.manager.entity.Manager;
import com.team.final8teamproject.manager.entity.ManagerRoleEnum;
import com.team.final8teamproject.manager.repository.ManagerRepository;
import com.team.final8teamproject.security.jwt.JwtUtil;
import com.team.final8teamproject.security.redis.RedisUtil;
import com.team.final8teamproject.user.repository.RefreshTokenRepository;
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
        String managerName = requestDto.getManager();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickName = requestDto.getNickname();

        Optional<Manager> found = managerRepository.findByManager(managerName);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        ManagerRoleEnum role = ManagerRoleEnum.WAIT;
        Manager manager = Manager.builder()
                .manager(managerName).password(password)
                .nickname(nickName).role(role)
                .build();
        managerRepository.save(manager);
        return new ManagerSignupResponseDto("신청에 성공했습니다.");
    }

    // 관리자 로그인
    @Override
    @Transactional
    public ManagerLoginResponseDto login(ManagerLoginRequestDto managerLoginRequestDto){

        return new ManagerLoginResponseDto("로그인 성공했습니다");
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
