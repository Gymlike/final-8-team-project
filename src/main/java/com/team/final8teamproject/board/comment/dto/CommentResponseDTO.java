package com.team.final8teamproject.board.comment.dto;

import com.team.final8teamproject.board.comment.commentReply.dto.CommentReplyResponseDTO;
import com.team.final8teamproject.board.comment.entity.T_exerciseComment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CommentResponseDTO {

    private final String content;

    private final String writerName;

    private final LocalDateTime creatAt;


    public CommentResponseDTO(T_exerciseComment t_exerciseComment) {
        this.content = t_exerciseComment.getComment();
        this.writerName = t_exerciseComment.getUsername();
        this.creatAt = t_exerciseComment.getCreatedAt();

    }


}
