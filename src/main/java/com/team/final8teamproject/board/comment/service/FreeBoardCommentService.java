package com.team.final8teamproject.board.comment.service;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.board.comment.dto.CreatCommentRequestDTO;
import com.team.final8teamproject.board.comment.entity.FreeBoardComment;
import com.team.final8teamproject.board.comment.entity.T_exerciseComment;
import com.team.final8teamproject.board.entity.FreeBoard;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FreeBoardCommentService {
    ResponseEntity<String> createComment(String comment, Long id, String userName,String userNickname);

    ResponseEntity<String> deleteComment(BaseEntity base, Long commentId);

//    List<T_exerciseComment> findCommentByUserName(String userName);

    List<FreeBoardComment> findCommentByBoardId(Long boardId);

    void deleteByBoardId(Long boardId);

    ResponseEntity<String> updateComment(CreatCommentRequestDTO requestDto, BaseEntity base, Long commentId);
}
