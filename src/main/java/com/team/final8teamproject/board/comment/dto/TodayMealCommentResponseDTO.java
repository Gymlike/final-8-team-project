package com.team.final8teamproject.board.comment.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.final8teamproject.board.comment.commentReply.dto.T_exerciseCommentReplyResponseDTO;
import com.team.final8teamproject.board.comment.commentReply.dto.TodayMealCommentReplyResponseDTO;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class TodayMealCommentResponseDTO {

    private final Long id;

    private final String content;

    private final String writerName;

    private final String nickname;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private final LocalDateTime creatDate;
    private final List<TodayMealCommentReplyResponseDTO> commentReplyList;

    public TodayMealCommentResponseDTO(Long id, String content, String writerName, LocalDateTime creatDate, List<TodayMealCommentReplyResponseDTO> commentReplyList,String nickname) {
        this.id = id;
        this.content = content;
        this.writerName = writerName;
        this.creatDate = creatDate;
        this.commentReplyList = commentReplyList;
        this.nickname = nickname;
    }
}
