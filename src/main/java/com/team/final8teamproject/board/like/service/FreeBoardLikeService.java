package com.team.final8teamproject.board.like.service;


import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.user.entity.User;
import org.springframework.http.ResponseEntity;

public interface FreeBoardLikeService {
    ResponseEntity<String> likeBoard(BaseEntity user, Long boardId);

    Long countLike(Long boardId);

    Long checkLike(User user, Long boardId);
}
