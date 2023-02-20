package com.team.final8teamproject.board.comment.commentReply.controller;

import com.team.final8teamproject.board.comment.commentReply.dto.CreatCommentReplyRequestDTO;
import com.team.final8teamproject.board.comment.commentReply.service.T_exerciseCommentReplyService;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/t-exercise")
public class T_exerciseCommentReplyController {

    private final T_exerciseCommentReplyService t_exerciseCommentReplyService;
    private final UserService userService;
    //대댓글 생성
    @PostMapping("/comment/{commentId}")
    public ResponseEntity<String> creatCommentReply(@PathVariable Long commentId,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                    @RequestBody CreatCommentReplyRequestDTO requestDTO){
        String username = userDetails.getBase().getUsername();
        String userNickname = userService.getUserNickname(userDetails.getBase());
//        String userNickname ="아 이거 유저닉네임 어떻게 꺼냄?!";
        String comment = requestDTO.getComment();
        return t_exerciseCommentReplyService.creatCommentRely(commentId,comment,username,userNickname);
    }

    //대댓글 수정
    @PutMapping("/comment/{commentId}")
    public ResponseEntity<String> updateCommentReply(@RequestBody CreatCommentReplyRequestDTO requestDTO,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @PathVariable Long commentId){

        String userName = userDetails.getUsername();

        return t_exerciseCommentReplyService.updateCommentReply(requestDTO,userName,commentId);
    }

    //대댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteCommentReply(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @PathVariable Long commentId) {
        return t_exerciseCommentReplyService.deleteCommentReply(userDetails.getUsername(),commentId);
    }
}
