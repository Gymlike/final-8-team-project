package com.team.final8teamproject.board.comment.dto;


import com.team.final8teamproject.board.comment.commentReply.dto.CommentReplyResponseDTO;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class CommentResponseDTO {

    private final Long id;

    private final String content;

    private final String writerName;

    private final LocalDateTime creatDate;
    private final List<CommentReplyResponseDTO> commentReplyList;

    public CommentResponseDTO(Long id, String content, String writerName, LocalDateTime creatDate, List<CommentReplyResponseDTO> commentReplyList) {
        this.id = id;
        this.content = content;
        this.writerName = writerName;
        this.creatDate = creatDate;
        this.commentReplyList = commentReplyList;
    }
}
