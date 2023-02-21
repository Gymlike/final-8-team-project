package com.team.final8teamproject.user.service;

import com.team.final8teamproject.manager.entity.Manager;
import com.team.final8teamproject.manager.repository.ManagerRepository;
import com.team.final8teamproject.owner.entity.Owner;
import com.team.final8teamproject.owner.repository.OwnerRepository;
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
    private final OwnerRepository ownerRepository;
    private final ManagerRepository managerRepository;

    //유저
    //1. 프로필 조회
    @Transactional
    public ProfileResponseDto getProfile(User user) {
        return new ProfileResponseDto(user.getId(), user.getUsername(), user.getNickName(), user.getEmail(), user.getProfileImage(), user.getPhoneNumber());
    }

    //2. 프로필 수정
    @Override
    @Transactional
    public void modifyProfile(ProfileModifyRequestDto profileModifyRequestDto, User user) {
        user.changeProfile(profileModifyRequestDto.getNickname(), profileModifyRequestDto.getImage(), profileModifyRequestDto.getPhoneNumber());
        userRepository.save(user);
    }


    //사업자
    //1. 프로필 조회
    @Transactional
    public ProfileResponseDto getOwnerProfile(Owner owner) {
        return new ProfileResponseDto(owner.getUsername(), owner.getNickName(), owner.getEmail(), owner.getProfileImage(), owner.getPhoneNumber());
    }
}
//    //2. 프로필 수정
//    @Override
//    @Transactional
//    public void modifyOwnerProfile(ProfileModifyRequestDto profileModifyRequestDto, Owner owner) {
//        owner.changeOwnerProfile(profileModifyRequestDto);
//        ownerRepository.save(owner);
//    }
//
//    //관리자
//    //1. 프로필 조회
//    @Transactional
//    public ProfileResponseDto getManagerProfile(Manager manager) {
//        return new ProfileResponseDto(manager.getUsername(), manager.getNickName(), manager.getEmail() ,manager.getProfileImage(), manager.getPhoneNumber());
//    }
//
//    //2. 프로필 수정
//    @Override
//    @Transactional
//    public void modifyManagerProfile(ProfileModifyRequestDto profileModifyRequestDto, Manager manager) {
//        manager.changeManagerProfile(profileModifyRequestDto);
//        managerRepository.save(manager);
//    }
//
//}
