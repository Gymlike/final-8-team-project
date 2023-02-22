package com.team.final8teamproject.gymboardreview.dto;

import com.team.final8teamproject.gymboardreview.entity.GymReview;
import lombok.Getter;

@Getter
public class GymBoardReviewResponseDto {

    private Long id;
    private String comment;
    private Long rating;
    public GymBoardReviewResponseDto(GymReview review){
        this.comment = review.getComment();
        this.rating = review.getRating();
        this.id = review.getId();
    }
}
