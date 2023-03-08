package com.team.final8teamproject.gymboard.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.final8teamproject.gymboard.entity.GymBoard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GymPostResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String image;
    private final String gymName;
    private final String location;

    private final Long rating;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'작성시간'HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdDate;
    public GymPostResponseDto(GymBoard gymBoard) {
        this.id = gymBoard.getId();
        this.title = gymBoard.getTitle();
        this.content =gymBoard.getContent();
        this.image =gymBoard.getImageUrl();
        this.gymName =gymBoard.getGymName();
        this.location = gymBoard.getRegion();
        this.createdDate =gymBoard.getCreatedDate();
        this.rating = gymBoard.getRating();
    }
}
