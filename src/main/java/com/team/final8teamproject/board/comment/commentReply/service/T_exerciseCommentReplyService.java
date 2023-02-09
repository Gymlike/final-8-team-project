package com.team.final8teamproject.board.comment.commentReply.service;

import com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface T_exerciseCommentReplyService {
    ResponseEntity<String> creatCommentRely(Long commentId, String comment, String username);

}
