package com.team.final8teamproject.user.controller;

import com.team.final8teamproject.security.service.OwnerDetailsImpl;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.user.dto.CreatePostGymRequestDto;
import com.team.final8teamproject.user.dto.GymPostResponseDto;
import com.team.final8teamproject.user.service.GymPostService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gym")
@RequiredArgsConstructor
public class GymBoardController {

    private final GymPostService gymPostService;

    @GetMapping()//
    public List<GymPostResponseDto> getGymPosts(@AuthenticationPrincipal OwnerDetailsImpl ownerDetails) {
        return gymPostService.getGymPost(ownerDetails.getUsername());
    }

    @GetMapping("/myposts")
    public List<GymPostResponseDto> getAllPosts(@PageableDefault Pageable pageable, @AuthenticationPrincipal OwnerDetailsImpl ownerDetails) {
        return gymPostService.getAllGymPost(pageable.getPageNumber(), ownerDetails.getUsername());
    }

    //운동시설 글 작성
    @PostMapping("")
    public String createGymPost(@RequestBody CreatePostGymRequestDto createPostGymRequestDto, @AuthenticationPrincipal OwnerDetailsImpl ownerDetails) {
        return gymPostService.createGymPost(createPostGymRequestDto, ownerDetails.getUsername());
    }

    //운동시설 글 수정
    @PutMapping("/{id}")
    public GymPostResponseDto updateGymPost(@PathVariable Long id, @RequestBody CreatePostGymRequestDto requestDto, @AuthenticationPrincipal OwnerDetailsImpl ownerDetails) {
        return gymPostService.updateGymPost(requestDto, ownerDetails.getUsername(), id);
    }

    //운동시설 글 삭제
    @DeleteMapping("/{id}")
    public String deleteGymPost(@PathVariable Long id, @AuthenticationPrincipal OwnerDetailsImpl ownerDetails) {
        return gymPostService.deleteGymPost(id, ownerDetails.getUsername());
    }


}
