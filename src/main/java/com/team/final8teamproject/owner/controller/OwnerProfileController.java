package com.team.final8teamproject.owner.controller;


import com.team.final8teamproject.owner.dto.OwnerProfileModifyRequestDto;
import com.team.final8teamproject.owner.dto.OwnerProfileResponseDto;
import com.team.final8teamproject.owner.service.OwnerProfileService;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/owners")
public class OwnerProfileController {
    private final OwnerProfileService ownerProfileService;

    //1. 프로필 조회
    @GetMapping("")
    public OwnerProfileResponseDto getMyOwnerProfile(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return ownerProfileService.getMyOwnerProfile(userDetailsImpl.getBase().getId());
    }

    //2. 프로필 수정
    @PatchMapping("")
    public void modifyMyOwnerProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid OwnerProfileModifyRequestDto ownerProfileRequestDto) {
        ownerProfileService.modifyMyOwnerProfile(ownerProfileRequestDto,userDetails.getBase().getId());
    }
}
