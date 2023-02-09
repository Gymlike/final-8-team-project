package com.team.final8teamproject.board.comment.commentReply.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentReplyResponseDTO {
    private final String content;

    private final String writerName;

    private final LocalDateTime creatAt;

    public CommentReplyResponseDTO(String content, String writerName, LocalDateTime creatAt) {
        this.content = content;
        this.writerName = writerName;
        this.creatAt = creatAt;
    }
}
