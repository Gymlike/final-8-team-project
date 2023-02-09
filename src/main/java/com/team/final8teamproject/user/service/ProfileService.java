package com.team.final8teamproject.user.service;

import com.team.final8teamproject.user.dto.ProfileModifyRequestDto;
import com.team.final8teamproject.user.dto.ProfileResponseDto;
import com.team.final8teamproject.user.entity.User;

public interface ProfileService {

    ProfileResponseDto getProfile(User user);

    void modifyProfile(ProfileModifyRequestDto profileModifyRequestDto, User user);

}
