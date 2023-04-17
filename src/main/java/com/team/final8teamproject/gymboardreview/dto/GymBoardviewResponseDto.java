package com.team.final8teamproject.gymboardreview.dto;

import com.team.final8teamproject.gymboard.entity.GymBoard;
import lombok.Getter;

@Getter
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
        this.image =gymBoard.getImageUrl();
        this.gymName =gymBoard.getGymName();
        this.location = gymBoard.getRegion();
    }
}
