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
    private String gymName;
    private String location;
    private String ownerNumber;

    public GymPostResponseDto(GymBoard gymBoard) {
        this.id = gymBoard.getId();
        this.title = gymBoard.getTitle();
        this.username =gymBoard.getUsername();
        this.content =gymBoard.getContent();
        this.image =gymBoard.getGymImage();
        this.gymName =gymBoard.getGymName();
        this.location = gymBoard.getRegion();
        this.ownerNumber= gymBoard.getOwnerNumber();
    }
}
