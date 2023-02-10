package com.team.final8teamproject.user.controller;

import com.team.final8teamproject.security.userservice.UserDetailsImpl;
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

    //1. 프로필 조회
    @GetMapping()
    public ProfileResponseDto getProfile(@Valid @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return profileService.getProfile(userDetailsImpl.getUser());
    }

    //2. 프로필 수정
    @PostMapping()
    public void modifyProfile(@Valid @RequestBody ProfileModifyRequestDto profileModifyRequestDto
            , @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        profileService.modifyProfile(profileModifyRequestDto, userDetailsImpl.getUser());
    }

}
