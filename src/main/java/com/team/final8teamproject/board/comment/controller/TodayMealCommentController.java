package com.team.final8teamproject.board.comment.controller;

import com.team.final8teamproject.board.comment.dto.CreatCommentRequestDTO;
import com.team.final8teamproject.board.comment.service.T_exerciseCommentService;
import com.team.final8teamproject.board.comment.service.TodayMealCommentService;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todaymeal")
@RequiredArgsConstructor
public class TodayMealCommentController {
    private final TodayMealCommentService todayMealCommentService;
    private final UserService userService;
    //댓글작성
    @PostMapping("/{boardId}/comment")
    public ResponseEntity<String> createComment(@RequestBody CreatCommentRequestDTO requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long boardId) {
        String comment = requestDto.getComment();
        String userName = userDetails.getUsername();
        String userNickname = userService.getUserNickname(userDetails.getBase());
        return todayMealCommentService.createComment(comment, boardId,userName,userNickname);
    }

    //댓글삭제
    @DeleteMapping("/{boardId}/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {
        return todayMealCommentService.deleteComment((User) userDetails.getBase(), commentId);
    }

    //댓글수정
    @PutMapping("/{boardId}/comment/{commentId}")
    public ResponseEntity<String> updateComment(@RequestBody CreatCommentRequestDTO requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {

        return todayMealCommentService.updateComment(requestDto, (User) userDetails.getBase(), commentId);
    }
}