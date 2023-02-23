package com.team.final8teamproject.user.service;

import com.team.final8teamproject.user.dto.ProfileModifyRequestDto;
import com.team.final8teamproject.user.dto.ProfileResponseDto;

public interface ProfileService {
    //유저
    void modifyProfile(ProfileModifyRequestDto profileModifyRequestDto, Long id);

    ProfileResponseDto getProfile(Long id);

    //관리자
    void modifyManagerProfile(ProfileModifyRequestDto profileModifyRequestDto, Long id);

    ProfileResponseDto getManagerProfile(Long id);

    ProfileResponseDto getKakaoProfile(Long id);

//    ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ이전 버전ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
//    //유저
//    ProfileResponseDto getProfile(User user);
//
//    void modifyProfile(ProfileModifyRequestDto profileModifyRequestDto, User user);
//
//    //관리자
//    ProfileResponseDto getManagerProfile(Manager manager);
//
//    void modifyManagerProfile(ProfileModifyRequestDto profileModifyRequestDto, Manager manager);
//
//    //사업자
//    ProfileResponseDto getOwnerProfile(Owner owner);
//
//    void modifyOwnerProfile(ProfileModifyRequestDto profileModifyRequestDto, Owner owner);
}
