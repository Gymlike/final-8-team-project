package com.team.final8teamproject.board.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(force = true)
public class CreatCommentRequestDTO implements Serializable {
    private final String comment;
    public CreatCommentRequestDTO(String comment) {
        this.comment = comment;
    }
}
