package com.team.final8teamproject.board.dto;

import lombok.Getter;

@Getter
public class CreatT_exerciseBordRequestDTO {

    private final String title;

    private final String content;

    public CreatT_exerciseBordRequestDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

}