package com.team.final8teamproject.gymboard.dto;

import com.team.final8teamproject.gymboard.entity.GymBoard;
import lombok.Getter;

@Getter
public class GymPostPutResponseDto {
    private String title;
    private String content;
    private String image;
    private String gymName;
    private String location;
    private String ownerNumber;


    public GymPostPutResponseDto(GymBoard gymBoard) {
        this.title = gymBoard.getTitle();
        this.content =gymBoard.getContent();
        this.image =gymBoard.getImageUrl();
        this.gymName =gymBoard.getGymName();
        this.location = gymBoard.getRegion();
        this.ownerNumber= gymBoard.getOwnerNumber();
    }
}
