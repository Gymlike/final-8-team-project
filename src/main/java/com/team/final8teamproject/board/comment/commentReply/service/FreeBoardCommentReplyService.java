package com.team.final8teamproject.board.comment.commentReply.service;

import com.team.final8teamproject.board.comment.commentReply.dto.CreatCommentReplyRequestDTO;
import org.springframework.http.ResponseEntity;

public interface FreeBoardCommentReplyService {
    ResponseEntity<String> creatCommentRely(Long commentId, String comment, String username,String nickname);




    ResponseEntity<String> updateCommentReply(CreatCommentReplyRequestDTO requestDTO, String username, Long commentID);

    ResponseEntity<String> deleteCommentReply(String username, Long commentId);
}
