package com.team.final8teamproject.comment.service;

import com.team.final8teamproject.comment.dto.CreatT_exerciseCommentRequestDTO;
import com.team.final8teamproject.user.entity.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public interface T_exerciseCommentService {
    ResponseEntity<String> createComment(String comment, Long id, String userName);
}
