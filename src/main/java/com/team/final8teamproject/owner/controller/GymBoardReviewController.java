package com.team.final8teamproject.owner.controller;

import com.team.final8teamproject.owner.dto.GymBoardReviewRequestDto;
import com.team.final8teamproject.owner.dto.GymReviewUpdateRequestDto;
import com.team.final8teamproject.owner.service.GymBoardReviewService;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gymreview")
@RequiredArgsConstructor
public class GymBoardReviewController {

    private final GymBoardReviewService gymBoardReviewService;
    //1.운동시설 리뷰 작성
    @PostMapping("/write")
    public String writeReview(@RequestBody GymBoardReviewRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        gymBoardReviewService.writeReview(requestDto, userDetails.getUsername());
        return "작성 성공";
    }

    //2. 운동시설 리뷰 조회
//    @GetMapping("")

    //3. 운동시설 리뷰 수정
    @PutMapping("/putreview/{id}")
    public String putReview(@PathVariable Long id, @RequestBody GymReviewUpdateRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return gymBoardReviewService.putReview(requestDto, userDetails.getUsername(), id);
    }
    //4. 운동시설 리뷰 삭제
    @DeleteMapping("/deletereview/{id}")
    public String deleteReview(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return gymBoardReviewService.deleteReview(userDetails.getUsername(), id);
    }
}
