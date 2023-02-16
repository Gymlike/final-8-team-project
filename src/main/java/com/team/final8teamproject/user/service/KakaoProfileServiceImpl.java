package com.team.final8teamproject.user.service;

import com.team.final8teamproject.user.dto.KakaoProfileResponseDto;
import com.team.final8teamproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KakaoProfileServiceImpl implements KakaoProfileService {

    //1. 프로필 조회
    @Transactional
    public KakaoProfileResponseDto getProfile(User user) {
        return new KakaoProfileResponseDto(user.getUsername(), user.getNickName(), user.getEmail(), user.getProfileImage(), user.getPhoneNumber());
    }
}
