package com.team.final8teamproject.board.like.service;


import com.team.final8teamproject.user.entity.User;
import org.springframework.http.ResponseEntity;

public interface TodayMealLikeService {
    ResponseEntity<String> likeBoard(User user, Long boardId);

    Long countLike(Long boardId);
}
