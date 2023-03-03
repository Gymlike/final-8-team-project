package com.team.final8teamproject.board.comment.commentReply.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.final8teamproject.board.comment.commentReply.entity.FreeBoardCommentReply;
import com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply;
import com.team.final8teamproject.board.entity.FreeBoard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FreeBoardCommentReplyResponseDTO {

    private final Long id;
    private final String content;

    private final String writerName;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private final LocalDateTime creatDate;

    private final String nickname;


    public FreeBoardCommentReplyResponseDTO(FreeBoardCommentReply freeBoardCommentReply) {
        this.content = freeBoardCommentReply.getCommentContent();
        this.writerName = freeBoardCommentReply.getUsername();
        this.creatDate = freeBoardCommentReply.getCreatedDate();
        this.id = freeBoardCommentReply.getId();
        this.nickname=freeBoardCommentReply.getUserNickname();
    }
}
