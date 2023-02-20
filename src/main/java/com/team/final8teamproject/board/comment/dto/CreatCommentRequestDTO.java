package com.team.final8teamproject.board.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class CreatCommentRequestDTO {
    private final String comment;
    public CreatCommentRequestDTO(String comment) {
        this.comment = comment;
    }
}
