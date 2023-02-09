package com.team.final8teamproject.board.comment.commentReply.service;

import com.team.final8teamproject.board.comment.commentReply.dto.CreatT_exerciseCommentReplyRequestDTO;
import com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply;
import com.team.final8teamproject.user.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface T_exerciseCommentReplyService {
    ResponseEntity<String> creatCommentRely(Long commentId, String comment, String username);

    ResponseEntity<String> updateCommentReply(CreatT_exerciseCommentReplyRequestDTO requestDTO, User user, Long commentID);

    ResponseEntity<String> deleteCommentReply(User user, Long commentId);
}
