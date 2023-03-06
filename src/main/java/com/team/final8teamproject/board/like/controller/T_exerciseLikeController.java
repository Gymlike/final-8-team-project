package com.team.final8teamproject.board.like.controller;


import com.team.final8teamproject.board.like.service.T_exerciseLikeService;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/t-exercise")
public class T_exerciseLikeController {

    private final T_exerciseLikeService tExerciseLikeService;
    //좋아요 누르기
    @PostMapping("/{boardId}/like")
    public ResponseEntity<String> likeBoard(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails ){

            return tExerciseLikeService.likeBoard(userDetails.getBase(),boardId);
    }

    @GetMapping("/{boardId}/like")
    private Long countLike(@PathVariable Long boardId){
        return tExerciseLikeService.countLike(boardId);
    }

    @GetMapping("/{boardId}/checklike")
    private Long checkLike(@PathVariable Long boardId,@AuthenticationPrincipal UserDetailsImpl userDetails){

        return tExerciseLikeService.checkLike((User) userDetails.getBase(),boardId);
    }


}
