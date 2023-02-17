package com.team.final8teamproject.owner.dto;

import com.team.final8teamproject.owner.entity.GymReview;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class GymBoardReviewResponseDto {
    private String comment;

    public GymBoardReviewResponseDto(GymReview review){
        this.comment = review.getComment();
    }
}
