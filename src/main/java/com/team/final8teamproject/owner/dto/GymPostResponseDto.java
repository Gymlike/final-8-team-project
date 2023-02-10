package com.team.final8teamproject.owner.dto;

import com.team.final8teamproject.owner.entity.GymBoard;
import lombok.Getter;

@Getter
public class GymPostResponseDto {
    private Long id;
    private String title;
    private String username;
    private String content;
    private String image;
    private String trainer;
    private String location;

    public GymPostResponseDto(GymBoard gymBoard) {
        this.id = gymBoard.getId();
        this.title = gymBoard.getTitle();
        this.username =gymBoard.getUserName();
        this.content =gymBoard.getContent();
        this.image =gymBoard.getImage();
        this.trainer =gymBoard.getTrainer();
        this.location = gymBoard.getRegion();
    }
}
