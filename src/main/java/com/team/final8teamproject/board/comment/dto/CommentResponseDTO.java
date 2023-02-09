package com.team.final8teamproject.board.comment.dto;


import com.team.final8teamproject.board.comment.commentReply.dto.CommentReplyResponseDTO;
import com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply;
import com.team.final8teamproject.board.comment.entity.T_exerciseComment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class CommentResponseDTO {

    private final String content;

    private final String writerName;

    private final LocalDateTime creatAt;
    private final List<CommentReplyResponseDTO> commentReplyList;

    public CommentResponseDTO(String content, String writerName, LocalDateTime creatAt, List<CommentReplyResponseDTO> commentReplyList) {
        this.content = content;
        this.writerName = writerName;
        this.creatAt = creatAt;
        this.commentReplyList = commentReplyList;
    }
}
