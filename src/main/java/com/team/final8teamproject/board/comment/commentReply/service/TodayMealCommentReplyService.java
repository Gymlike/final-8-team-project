package com.team.final8teamproject.board.comment.commentReply.service;

import com.team.final8teamproject.board.comment.commentReply.dto.CreatCommentReplyRequestDTO;
import com.team.final8teamproject.user.entity.User;
import org.springframework.http.ResponseEntity;

public interface TodayMealCommentReplyService {
    ResponseEntity<String> creatCommentRely(Long commentId, String comment, String username);

    ResponseEntity<String> updateCommentReply(CreatCommentReplyRequestDTO requestDTO, User user, Long commentID);

    ResponseEntity<String> deleteCommentReply(User user, Long commentId);
}
