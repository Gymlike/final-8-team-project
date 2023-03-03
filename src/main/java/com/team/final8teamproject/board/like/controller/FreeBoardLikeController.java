package com.team.final8teamproject.board.like.controller;

import com.team.final8teamproject.board.like.service.FreeBoardLikeService;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/freeboard")
public class FreeBoardLikeController {

    private final FreeBoardLikeService freeBoardLikeService;
    //좋아요 누르기
    @PostMapping("/{boardId}/like")
    public ResponseEntity<String> likeBoard(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails ){

            return freeBoardLikeService.likeBoard(userDetails.getBase(),boardId);
    }
}
