package com.team.final8teamproject.manager.service;

import com.team.final8teamproject.manager.dto.ManagerLoginRequestDto;
import com.team.final8teamproject.manager.dto.ManagerLoginResponseDto;
import com.team.final8teamproject.manager.dto.ManagerSignupRequestDto;
import com.team.final8teamproject.manager.dto.ManagerSignupReseponseDto;
import com.team.final8teamproject.manager.repository.ManagerRepository;
import com.team.final8teamproject.security.jwt.JwtUtil;
import com.team.final8teamproject.security.redis.RedisUtil;
import com.team.final8teamproject.user.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService{
    private final ManagerRepository managerRepository;
    private static final String MANAGER_TOKEN = "D1d@A$5dm4&4D1d1i34n%7";
    private final RedisUtil redisUtil;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public ManagerSignupReseponseDto signup(ManagerSignupRequestDto managerSignupRequestDto){

        return null;
    }
    @Override
    @Transactional
    public ManagerLoginResponseDto login(ManagerLoginRequestDto managerLoginRequestDto){

        return null;
    }
    @Override
    @Transactional
    public void logout(){

    }
}
