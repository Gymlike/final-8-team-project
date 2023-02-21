package com.team.final8teamproject.board.comment.commentReply.service;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.board.comment.commentReply.dto.CreatCommentReplyRequestDTO;
import com.team.final8teamproject.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface T_exerciseCommentReplyService {
    ResponseEntity<String> creatCommentRely(Long commentId, String comment, String username,String nickname);




    ResponseEntity<String> updateCommentReply(CreatCommentReplyRequestDTO requestDTO, String username, Long commentID);

    ResponseEntity<String> deleteCommentReply(String username, Long commentId);
}
