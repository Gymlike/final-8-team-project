package com.team.final8teamproject.board.comment.commentReply.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply;
import com.team.final8teamproject.board.comment.commentReply.entity.TodayMealCommentReply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodayMealCommentReplyResponseDTO {

    private final Long id;
    private final String content;

    private final String writerName;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private final LocalDateTime creatDate;

    private final String nickname;

    public TodayMealCommentReplyResponseDTO(TodayMealCommentReply todayMealCommentReply) {
        this.content = todayMealCommentReply.getCommentContent();
        this.writerName = todayMealCommentReply.getUsername();
        this.creatDate = todayMealCommentReply.getCreatedDate();
        this.id = todayMealCommentReply.getId();
        this.nickname = todayMealCommentReply.getUserNickname();
    }
}
