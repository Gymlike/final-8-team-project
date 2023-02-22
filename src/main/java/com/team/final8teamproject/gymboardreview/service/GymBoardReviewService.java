package com.team.final8teamproject.gymboardreview.service;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.gymboardreview.dto.GymBoardReviewRequestDto;
import com.team.final8teamproject.gymboardreview.dto.GymBoardReviewResponseDto;
import com.team.final8teamproject.gymboardreview.dto.GymReviewUpdateRequestDto;
import com.team.final8teamproject.gymboardreview.entity.GymReview;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public interface GymBoardReviewService {
    //1.리뷰 작성
    public void writeReview(Long id, GymBoardReviewRequestDto requestDto, String username);

    //2.작성된 리뷰조회
    public List<GymBoardReviewResponseDto> getReview(Long gymId);

    //3. 작성된 리뷰 수정
    public String putReview(GymReviewUpdateRequestDto requestDto, String username, Long id);
    //4. 작성된 리뷰 삭제
    public String deleteReview(String username, Long id);
}
