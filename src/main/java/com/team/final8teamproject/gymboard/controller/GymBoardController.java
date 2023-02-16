package com.team.final8teamproject.gymboard.controller;

import com.team.final8teamproject.gymboard.dto.*;
import com.team.final8teamproject.gymboard.service.GymPostService;
import com.team.final8teamproject.security.service.UserDetailsImpl;
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

    //1.운동시설 글 작성
    @PostMapping("/newgym")
    public String createGymPost(@RequestBody CreatePostGymRequestDto createPostGymRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return gymPostService.createGymPost(createPostGymRequestDto, userDetails.getUsername());
    }

    //2.유저가하는 작성된 운동시설 조회
    @GetMapping()//
    public List<GymPostResponseDto> getGymPosts() {
        return gymPostService.getGymPost();
    }
    //2.유저가하는 작성된 운동시설 하나 조회
    @GetMapping("/{id}")//
    public GymPostResponseDetailDto getGymPostDetail(@PathVariable Long id) {
        return gymPostService.getGymPostDetail(id);
    }
    //3.사업자가하는 자기가 작성한 게시글 전체조회
    @GetMapping("/myposts")
    public List<GymBoardviewResponseDto> getAllPosts(@PageableDefault Pageable pageable, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return gymPostService.getAllGymPost(pageable.getPageNumber(), userDetails.getUsername());
    }

    //운동시설 글 수정
    @PutMapping("/put/{id}")
    public GymPostPutResponseDto updateGymPost(@PathVariable Long id, @RequestBody CreatePostGymRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return gymPostService.updateGymPost(requestDto, userDetails.getUsername(), id);
    }

    //운동시설 글 삭제
    @DeleteMapping("/delete/{id}")
    public String deleteGymPost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return gymPostService.deleteGymPost(id, userDetails.getUsername());
    }
}
