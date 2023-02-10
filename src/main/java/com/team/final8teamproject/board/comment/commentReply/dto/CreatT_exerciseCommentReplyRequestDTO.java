package com.team.final8teamproject.board.comment.commentReply.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class CreatT_exerciseCommentReplyRequestDTO {

    private final String comment;

    public CreatT_exerciseCommentReplyRequestDTO(String comment) {
        this.comment = comment;
    }
}
