package com.team.final8teamproject.board.comment.commentReply.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class T_exerciseCommentReplyResponseDTO {

    private final Long id;
    private final String content;

    private final String writerName;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private final LocalDateTime creatDate;

    private final String nickname;


    public T_exerciseCommentReplyResponseDTO(T_exerciseCommentReply t_exerciseCommentReply) {
        this.content = t_exerciseCommentReply.getCommentContent();
        this.writerName = t_exerciseCommentReply.getUsername();
        this.creatDate = t_exerciseCommentReply.getCreatedDate();
        this.id = t_exerciseCommentReply.getId();
        this.nickname=t_exerciseCommentReply.getUserNickname();
    }
}
