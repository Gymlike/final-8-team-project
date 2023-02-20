package com.team.final8teamproject.gymboard.controller;

import com.team.final8teamproject.gymboard.dto.*;
import com.team.final8teamproject.gymboard.service.GymPostServiceImpl;
import com.team.final8teamproject.gymboardreview.dto.GymBoardviewResponseDto;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/gym")
@RequiredArgsConstructor
public class GymBoardController {

    private final GymPostServiceImpl gymPostService;

    //1.운동시설 글 작성
    @PostMapping("/owner/write_post")
    public String createGymPost(@RequestPart("CreatePostGymRequestDto") CreatePostGymRequestDto createPostGymRequestDto,
                                @RequestPart("file") MultipartFile file,
                                @AuthenticationPrincipal UserDetailsImpl userDetails)  throws NullPointerException, IOException {
        return gymPostService.createGymPost(createPostGymRequestDto, file, userDetails.getUsername());
    }

    //2.유저가하는 작성된 운동시설 조회
    @GetMapping//
    public List<GymPostResponseDto> getGymPosts() {
        return gymPostService.getGymPost();
    }
    //3.유저가하는 작성된 운동시설 하나 조회
    @GetMapping("/{id}")//
    public GymPostResponseDetailDto getGymPostDetail(@PathVariable Long id) {
        return gymPostService.getGymPostDetail(id);
    }
    //4.사업자가하는 자기가 작성한 게시글 전체조회
    @GetMapping("/owner/myposts")
    public List<GymBoardviewResponseDto> getAllPosts(@PageableDefault Pageable pageable, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return gymPostService.getAllGymPost(pageable.getPageNumber(), userDetails.getUsername());
    }

    //5.운동시설 글 수정
    @PutMapping("/owner/put/{id}")
    public GymPostPutResponseDto updateGymPost(@PathVariable Long id, @RequestBody CreatePostGymRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return gymPostService.updateGymPost(requestDto, userDetails.getUsername(), id);
    }

    //6.운동시설 글 삭제
    @DeleteMapping("/owner/delete/{id}")
    public String deleteGymPost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return gymPostService.deleteGymPost(id, userDetails.getUsername());
    }
}
