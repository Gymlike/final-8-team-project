package com.team.final8teamproject.owner.controller;

import com.team.final8teamproject.owner.dto.GymRequestDto;
import com.team.final8teamproject.owner.service.OwnerGymService;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gymowner")
@RequiredArgsConstructor
public class OwnerGymController {
    private final OwnerGymService ownerGymService;
    public String crateGym(@RequestBody GymRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        ownerGymService.crateGym(userDetails.getUsername(), requestDto);
        return "성공";
    }
}
