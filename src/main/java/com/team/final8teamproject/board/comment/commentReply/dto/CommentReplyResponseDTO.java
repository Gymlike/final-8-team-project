package com.team.final8teamproject.board.comment.commentReply.dto;

import com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentReplyResponseDTO {
    private final String content;

    private final String writerName;

    private final LocalDateTime creatAt;

    public CommentReplyResponseDTO(T_exerciseCommentReply t_exerciseCommentReply) {
        this.content = t_exerciseCommentReply.getCommentContent();
        this.writerName = t_exerciseCommentReply.getUsername();
        this.creatAt = t_exerciseCommentReply.getCreatedAt();
    }
}
