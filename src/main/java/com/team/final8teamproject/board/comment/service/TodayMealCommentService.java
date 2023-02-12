package com.team.final8teamproject.board.comment.service;

import com.team.final8teamproject.board.comment.dto.CreatCommentRequestDTO;
import com.team.final8teamproject.board.comment.entity.T_exerciseComment;
import com.team.final8teamproject.user.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TodayMealCommentService {
    ResponseEntity<String> createComment(String comment, Long id, String userName);

    ResponseEntity<String> deleteComment(User user, Long commentId);

//    List<T_exerciseComment> findCommentByUserName(String userName);

    List<T_exerciseComment> findCommentByBoardId(Long boardId);

    void deleteByBoardId(Long boardId);

    ResponseEntity<String> updateComment(CreatCommentRequestDTO requestDto, User user, Long commentId);
}
