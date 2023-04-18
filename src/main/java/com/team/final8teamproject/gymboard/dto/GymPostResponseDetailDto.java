package com.team.final8teamproject.gymboard.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.team.final8teamproject.gymboard.entity.Amenities;
import com.team.final8teamproject.gymboard.entity.GymBoard;
import com.team.final8teamproject.gymboardreview.dto.GymBoardReviewResponseDto;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@Getter
public class GymPostResponseDetailDto implements Serializable {
    private Long id;
    private String title;
    private String username;
    private String content;
    private String image;
    private String gymName;
    private String location;
    private String ownerNumber;
    private String price;
    private String openTime;
    private Amenities amenities;
    private String amenitiesDetail;
    private String discountPrice;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    private List<GymBoardReviewResponseDto> postReview;
    public GymPostResponseDetailDto(){}
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
