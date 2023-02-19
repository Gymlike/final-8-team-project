package com.team.final8teamproject.gymboard.dto;

import com.team.final8teamproject.gymboard.entity.GymBoard;
import lombok.Getter;
import java.util.List;
@Getter
public class GymPostResponseDetailDto {
    private Long id;
    private String title;
    private String username;
    private String content;
    private String image;
    private String gymName;
    private String location;
    private String ownerNumber;

    private List<GymBoardReviewResponseDto> postReview;

    public GymPostResponseDetailDto(GymBoard gymBoard, List<GymBoardReviewResponseDto> postReview) {
        this.id = gymBoard.getId();
        this.title = gymBoard.getTitle();
        this.username =gymBoard.getUsername();
        this.content =gymBoard.getContent();
        this.image =gymBoard.getGymImage();
        this.gymName =gymBoard.getGymName();
        this.location = gymBoard.getRegion();
        this.ownerNumber= gymBoard.getOwnerNumber();
        this.postReview = postReview;
    }
}
