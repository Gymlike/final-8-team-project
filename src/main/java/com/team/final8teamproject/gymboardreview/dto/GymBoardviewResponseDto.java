package com.team.final8teamproject.gymboardreview.dto;

import com.team.final8teamproject.gymboard.entity.GymBoard;

public class GymBoardviewResponseDto {

    private Long id;
    private String title;
    private String content;
    private String image;
    private String gymName;
    private String location;


    public GymBoardviewResponseDto(GymBoard gymBoard) {
        this.id = gymBoard.getId();
        this.title = gymBoard.getTitle();
        this.content =gymBoard.getContent();
        this.image =gymBoard.getGymImage();
        this.gymName =gymBoard.getGymName();
        this.location = gymBoard.getRegion();
    }
}
