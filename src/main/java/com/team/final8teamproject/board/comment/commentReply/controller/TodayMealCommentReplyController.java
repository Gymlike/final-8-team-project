package com.team.final8teamproject.board.comment.commentReply.controller;

import com.team.final8teamproject.board.comment.commentReply.dto.CreatCommentReplyRequestDTO;
import com.team.final8teamproject.board.comment.commentReply.service.TodayMealCommentReplyService;
import com.team.final8teamproject.security.service.UserDetailsImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todaymeal")
public class TodayMealCommentReplyController {

    private final TodayMealCommentReplyService todayMealCommentReplyService;

    //대댓글 생성
    @PostMapping("/comment/{commentId}")
    public ResponseEntity<String> creatCommentReply(@PathVariable Long commentId,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                    @RequestBody CreatCommentReplyRequestDTO requestDTO){
        String username = userDetails.getUsername();
        String comment = requestDTO.getComment();
        String userNickname = userDetails.getBase().getNickName();
        return todayMealCommentReplyService.creatCommentRely(commentId,comment,username,userNickname);
    }

    //대댓글 수정
    @PutMapping("/comment/{commentId}")
    public ResponseEntity<String> updateCommentReply(@RequestBody CreatCommentReplyRequestDTO requestDTO,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @PathVariable Long commentId){
        String userName = userDetails.getUsername();

        return todayMealCommentReplyService.updateCommentReply(requestDTO,userName,commentId);
    }

    //대댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteCommentReply(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @PathVariable Long commentId) {
        return todayMealCommentReplyService.deleteCommentReply(userDetails.getBase(),commentId);
    }
}
