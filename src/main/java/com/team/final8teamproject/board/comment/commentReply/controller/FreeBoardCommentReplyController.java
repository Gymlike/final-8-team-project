package com.team.final8teamproject.board.comment.commentReply.controller;

import com.team.final8teamproject.board.comment.commentReply.dto.CreatCommentReplyRequestDTO;
import com.team.final8teamproject.board.comment.commentReply.service.FreeBoardCommentReplyService;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/freeboard")
public class FreeBoardCommentReplyController {

    private final FreeBoardCommentReplyService freeBoardCommentReplyService;

    //대댓글 생성
    @PostMapping("/comment/{commentId}")
    public ResponseEntity<String> creatCommentReply(@PathVariable Long commentId,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                    @RequestBody CreatCommentReplyRequestDTO requestDTO){
        String username = userDetails.getBase().getUsername();
        String userNickname = userDetails.getBase().getNickName();
//        String userNickname ="아 이거 유저닉네임 어떻게 꺼냄?!";
        String comment = requestDTO.getComment();
        return freeBoardCommentReplyService.creatCommentRely(commentId,comment,username,userNickname);
    }

    //대댓글 수정
    @PutMapping("/comment/{commentId}")
    public ResponseEntity<String> updateCommentReply(@RequestBody CreatCommentReplyRequestDTO requestDTO,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @PathVariable Long commentId){

        String userName = userDetails.getUsername();

        return freeBoardCommentReplyService.updateCommentReply(requestDTO,userName,commentId);
    }

    //대댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteCommentReply(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @PathVariable Long commentId) {
        return freeBoardCommentReplyService.deleteCommentReply(userDetails.getUsername(),commentId);
    }
}
