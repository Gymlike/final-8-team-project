package com.team.final8teamproject.board.comment.commentReply.service;

import org.springframework.http.ResponseEntity;

public interface T_exerciseCommentReplyService {
    ResponseEntity<String> creatCommentRely(Long commentId, String comment, String username);

}
