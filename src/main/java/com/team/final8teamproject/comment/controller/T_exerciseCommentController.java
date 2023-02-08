package com.team.final8teamproject.comment.controller;

import com.team.final8teamproject.comment.dto.CreatT_exerciseCommentRequestDTO;
import com.team.final8teamproject.comment.service.T_exerciseCommentService;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/t-exercise")
@RequiredArgsConstructor
public class T_exerciseCommentController {
    private final T_exerciseCommentService tExerciseCommentService;
    @PostMapping("/{id}/comment")
    public ResponseEntity<String> createComment(@RequestBody CreatT_exerciseCommentRequestDTO requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        String comment = requestDto.getComment();
        String userName = userDetails.getUser().getUsername();
        return tExerciseCommentService.createComment(comment, id,userName);
    }
}
