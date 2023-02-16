package com.team.final8teamproject.gymboard.dto;

import com.team.final8teamproject.gymboard.entity.GymReview;
import lombok.Getter;

@Getter
public class GymBoardReviewResponseDto {
    private String comment;

    public GymBoardReviewResponseDto(GymReview review){
        this.comment = review.getComment();
    }
}
