package com.team.final8teamproject.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class CreatBordRequestDTO {


    private final String title;

    private final String content;

    public CreatBordRequestDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

}