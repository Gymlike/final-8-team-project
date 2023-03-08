package com.team.final8teamproject.gymboard.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.final8teamproject.gymboard.entity.GymBoard;
import com.team.final8teamproject.gymboardreview.dto.GymBoardReviewResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
public class GymPostResponseDetailDto {
    private final Long id;
    private final String title;
    private final String username;
    private final String content;
    private final String image;
    private final String gymName;
    private final String location;
    private final String ownerNumber;
    private final String price;
    private final String openTime;
    private final String amenities;
    private final String amenitiesDetail;
    private final String discountPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdDate;
    private final List<GymBoardReviewResponseDto> postReview;

    public GymPostResponseDetailDto(GymBoard gymBoard,
                                    List<GymBoardReviewResponseDto> postReview) {
        this.id = gymBoard.getId();
        this.title = gymBoard.getTitle();
        this.username =gymBoard.getUsername();
        this.content =gymBoard.getContent();
        this.image =gymBoard.getImageUrl();
        this.gymName =gymBoard.getGymName();
        this.location = gymBoard.getRegion();
        this.createdDate = gymBoard.getCreatedDate();
        this.ownerNumber= gymBoard.getOwnerNumber();
        this.openTime = gymBoard.getOpenTime();
        this.amenitiesDetail = gymBoard.getAmenitiesDetail();
        this.amenities = gymBoard.getAmenities();
        this.price =gymBoard.getPrice();
        this.postReview = postReview;
        this.discountPrice = gymBoard.getDiscount();
    }
}
