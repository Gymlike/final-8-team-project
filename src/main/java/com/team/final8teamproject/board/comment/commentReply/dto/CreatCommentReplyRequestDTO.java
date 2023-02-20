package com.team.final8teamproject.board.comment.commentReply.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class CreatCommentReplyRequestDTO {

    private final String comment;

    public CreatCommentReplyRequestDTO(String comment) {
        this.comment = comment;
    }
}
