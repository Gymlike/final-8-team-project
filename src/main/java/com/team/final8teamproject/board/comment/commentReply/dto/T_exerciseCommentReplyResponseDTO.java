package com.team.final8teamproject.board.comment.commentReply.dto;

import com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class T_exerciseCommentReplyResponseDTO {

    private final Long id;
    private final String content;

    private final String writerName;

    private final LocalDateTime creatDate;

    public T_exerciseCommentReplyResponseDTO(T_exerciseCommentReply t_exerciseCommentReply) {
        this.content = t_exerciseCommentReply.getCommentContent();
        this.writerName = t_exerciseCommentReply.getUsername();
        this.creatDate = t_exerciseCommentReply.getCreatedDate();
        this.id = t_exerciseCommentReply.getId();
    }
}
