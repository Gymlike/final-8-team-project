package com.team.final8teamproject.board.comment.service;

import org.springframework.http.ResponseEntity;

public interface T_exerciseCommentService {
    ResponseEntity<String> createComment(String comment, Long id, String userName);
}
