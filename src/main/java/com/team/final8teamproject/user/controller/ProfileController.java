package com.team.final8teamproject.user.controller;

import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.user.dto.ProfileModifyRequestDto;
import com.team.final8teamproject.user.dto.ProfileResponseDto;
import com.team.final8teamproject.user.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    //유저
    //1. 프로필 조회
    @GetMapping("")
    public ProfileResponseDto getProfile(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return profileService.getProfile(userDetailsImpl.getBase().getId());
    }

    //2. 프로필 수정
    @PatchMapping("")
    public void modifyProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid ProfileModifyRequestDto profileModifyRequestDto) {
        profileService.modifyProfile(profileModifyRequestDto,userDetails.getBase().getId());
    }

    //관리자
    //1. 프로필 조회
    @GetMapping("/manager")
    public ProfileResponseDto getManagerProfile(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return profileService.getManagerProfile(userDetailsImpl.getBase().getId());
    }

    //2. 프로필 수정
    @PatchMapping("/manager")
    public void modifyManagerProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid ProfileModifyRequestDto profileModifyRequestDto) {
        profileService.modifyManagerProfile(profileModifyRequestDto,userDetails.getBase().getId());
    }

    @GetMapping("/kakao")
    public ProfileResponseDto getKakaoProfile(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return profileService.getKakaoProfile(userDetailsImpl.getBase().getId());
    }

//    ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ이전 버전ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
//    //유저
//    //1. 프로필 조회
//    @GetMapping()
//    public ProfileResponseDto getProfile(@Valid @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
//
//        return profileService.getProfile((User) userDetailsImpl.getBase());
//    }
//
//
//    //2. 프로필 수정
//    @PostMapping()
//    public void modifyProfile(@Valid @RequestBody ProfileModifyRequestDto profileModifyRequestDto
//            , @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
//        profileService.modifyProfile(profileModifyRequestDto, (User) userDetailsImpl.getBase());
//    }

//    //사업자
//    //1. 프로필 조회
//    @GetMapping("/owner")
//    public ProfileResponseDto getOwnerProfile(@Valid @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
//
//        return profileService.getOwnerProfile((Owner) userDetailsImpl.getBase());
//    }
//
//    //2. 프로필 수정
//    @PostMapping("/owner")
//    public void modifyOwnerProfile(@Valid @RequestBody ProfileModifyRequestDto profileModifyRequestDto
//            , @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
//        profileService.modifyOwnerProfile(profileModifyRequestDto, (Owner) userDetailsImpl.getBase());
//    }

//    //관리자
//    //1. 프로필 조회
//    @GetMapping("/manager")
//    public ProfileResponseDto getManagerProfile(@Valid @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
//
//        return profileService.getManagerProfile((Manager) userDetailsImpl.getBase());
//    }
//
//    //2. 프로필 수정
//    @PostMapping("/manager")
//    public void modifyManagerProfile(@Valid @RequestBody ProfileModifyRequestDto profileModifyRequestDto
//            , @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
//        profileService.modifyManagerProfile(profileModifyRequestDto, (Manager) userDetailsImpl.getBase());
//    }

}
