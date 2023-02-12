package com.team.final8teamproject.board.comment.commentReply.dto;

import com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply;
import com.team.final8teamproject.board.comment.commentReply.entity.TodayMealCommentReply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodayMealCommentReplyResponseDTO {

    private final Long id;
    private final String content;

    private final String writerName;

    private final LocalDateTime creatDate;

    public TodayMealCommentReplyResponseDTO(TodayMealCommentReply todayMealCommentReply) {
        this.content = todayMealCommentReply.getCommentContent();
        this.writerName = todayMealCommentReply.getUsername();
        this.creatDate = todayMealCommentReply.getCreatedDate();
        this.id = todayMealCommentReply.getId();
    }
}
