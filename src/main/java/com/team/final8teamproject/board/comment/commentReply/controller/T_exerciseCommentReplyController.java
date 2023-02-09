package com.team.final8teamproject.board.comment.commentReply.controller;

import com.team.final8teamproject.board.comment.commentReply.dto.CreatT_exerciseCommentReplyRequestDTO;
import com.team.final8teamproject.board.comment.commentReply.service.T_exerciseCommentReplyService;
import com.team.final8teamproject.security.userservice.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/t-exercise")
public class T_exerciseCommentReplyController {

    private final T_exerciseCommentReplyService t_exerciseCommentReplyService;
    //대댓글 생성
    @PostMapping("/comment/{commentId}")
    public ResponseEntity<String> creatCommentReply(@PathVariable Long commentId,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                    @RequestBody CreatT_exerciseCommentReplyRequestDTO requestDTO){
        String username = userDetails.getUser().getUsername();
        String comment = requestDTO.getComment();
        return t_exerciseCommentReplyService.creatCommentRely(commentId,comment,username);
    }

    //대댓글 수정
    @PutMapping("/comment/{commentId}")
    public ResponseEntity<String> updateCommentReply(@RequestBody CreatT_exerciseCommentReplyRequestDTO requestDTO,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @PathVariable Long commentId){
        return t_exerciseCommentReplyService.updateCommentReply(requestDTO,userDetails.getUser(),commentId);
    }

    //대댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteCommentReply(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @PathVariable Long commentId) {
        return t_exerciseCommentReplyService.deleteCommentReply(userDetails.getUser(),commentId);
    }
}
