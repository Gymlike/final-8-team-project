package com.team.final8teamproject.gymboardreview.controller;

import com.team.final8teamproject.gymboardreview.dto.GymBoardReviewRequestDto;
import com.team.final8teamproject.gymboardreview.dto.GymReviewUpdateRequestDto;
import com.team.final8teamproject.gymboardreview.service.GymBoardReviewService;
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
    @PostMapping("/{id}/write")
    public String writeReview(@PathVariable Long id, @RequestBody GymBoardReviewRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        gymBoardReviewService.writeReview(id, requestDto, userDetails.getUsername());
        return "작성 성공";
    }

    //2. 운동시설 리뷰 조회
    //    @GetMapping("")
    //GymBoard에서 알아서 호출해서 조회함

    //3. 운동시설 리뷰 수정
    @PutMapping("/{reviewId}/putreview")
    public String putReview(@PathVariable Long reviewId, @RequestBody GymReviewUpdateRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return gymBoardReviewService.putReview(requestDto, userDetails.getUsername(), reviewId);
    }
    //4. 운동시설 리뷰 삭제
    @DeleteMapping("/{reviewId}/deletereview")
    public String deleteReview(@PathVariable Long reviewId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return gymBoardReviewService.deleteReview(userDetails.getUsername(), reviewId);
    }
}
