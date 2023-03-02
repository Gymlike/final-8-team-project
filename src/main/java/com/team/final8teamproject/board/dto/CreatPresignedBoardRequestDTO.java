package com.team.final8teamproject.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
class CreatPresignedBoardRequestDTO {

    private final String title;

    private final String content;

    private final String imageName;

    public CreatPresignedBoardRequestDTO (String title, String content,String imageName) {
        this.title = title;
        this.content = content;
        this.imageName = imageName;
    }

}
