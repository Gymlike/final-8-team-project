package com.team.final8teamproject.board.comment.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.final8teamproject.board.comment.commentReply.dto.T_exerciseCommentReplyResponseDTO;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class T_exerciseCommentResponseDTO {

    private final Long id;

    private final String content;

    private final String writerName;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private final LocalDateTime creatDate;

    private final String nickname;
    private final List<T_exerciseCommentReplyResponseDTO> commentReplyList;

    public T_exerciseCommentResponseDTO(Long id, String content, String writerName, LocalDateTime creatDate, List<T_exerciseCommentReplyResponseDTO> commentReplyList,String nickname) {
        this.id = id;
        this.content = content;
        this.writerName = writerName;
        this.creatDate = creatDate;
        this.commentReplyList = commentReplyList;
        this.nickname = nickname;
    }
}
