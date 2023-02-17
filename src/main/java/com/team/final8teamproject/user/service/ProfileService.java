package com.team.final8teamproject.user.service;

import com.team.final8teamproject.owner.entity.Owner;
import com.team.final8teamproject.user.dto.ProfileModifyRequestDto;
import com.team.final8teamproject.user.dto.ProfileResponseDto;
import com.team.final8teamproject.user.entity.User;

public interface ProfileService {

    //유저
    ProfileResponseDto getProfile(User user);

    void modifyProfile(ProfileModifyRequestDto profileModifyRequestDto, User user);


    //사업자
    ProfileResponseDto getOwnerProfile(Owner owner);

    void modifyOwnerProfile(ProfileModifyRequestDto profileModifyRequestDto, Owner owner);

}
