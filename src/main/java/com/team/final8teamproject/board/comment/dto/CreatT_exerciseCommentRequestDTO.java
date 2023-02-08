package com.team.final8teamproject.board.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class CreatT_exerciseCommentRequestDTO {
    private final String comment;
    public CreatT_exerciseCommentRequestDTO(String comment) {
        this.comment = comment;
    }
}
