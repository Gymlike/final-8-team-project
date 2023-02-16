package com.team.final8teamproject.user.service;

import com.team.final8teamproject.user.dto.KakaoProfileResponseDto;
import com.team.final8teamproject.user.entity.User;

public interface KakaoProfileService {

    KakaoProfileResponseDto getProfile(User user);

}
