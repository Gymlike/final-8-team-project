package com.team.final8teamproject.user.service;

import com.team.final8teamproject.user.dto.ProfileModifyRequestDto;
import com.team.final8teamproject.user.dto.ProfileResponseDto;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;

    //1. 프로필 조회
    @Transactional
    public ProfileResponseDto getProfile(User user) {
        return new ProfileResponseDto(user.getUsername(), user.getNickName(), user.getEmail() ,user.getImage(), user.getPhoneNumber());
    }

    //2. 프로필 수정
    @Override
    @Transactional
    public void modifyProfile(ProfileModifyRequestDto profileModifyRequestDto, User user) {
        user.changeProfile(profileModifyRequestDto);
        userRepository.save(user);
    }

}
