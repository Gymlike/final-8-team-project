package com.team.final8teamproject.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(force = true)
public class CreatBordRequestDTO implements Serializable {


    private final String title;

    private final String content;

    public CreatBordRequestDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

}