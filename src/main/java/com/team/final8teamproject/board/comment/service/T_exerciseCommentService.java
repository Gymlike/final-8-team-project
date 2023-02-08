package com.team.final8teamproject.board.comment.service;

import com.team.final8teamproject.board.comment.dto.CreatT_exerciseCommentRequestDTO;
import com.team.final8teamproject.board.comment.entity.T_exerciseComment;
import com.team.final8teamproject.user.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface T_exerciseCommentService {
    ResponseEntity<String> createComment(String comment, Long id, String userName);

    ResponseEntity<String> deleteComment(User user, Long commentId);

    List<T_exerciseComment> findCommentByUserName(String userName);

    void deleteByBoardId(Long boardId);

    ResponseEntity<String> updateComment(CreatT_exerciseCommentRequestDTO requestDto, User user, Long commentId);
}
