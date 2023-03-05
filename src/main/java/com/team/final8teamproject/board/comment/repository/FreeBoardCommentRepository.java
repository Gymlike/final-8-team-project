package com.team.final8teamproject.board.comment.repository;

import com.team.final8teamproject.board.comment.entity.FreeBoardComment;
import com.team.final8teamproject.board.comment.entity.T_exerciseComment;
import com.team.final8teamproject.board.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeBoardCommentRepository extends JpaRepository<FreeBoardComment,Long> {

//    List<T_exerciseComment> findByUsername(String username);
    List<FreeBoardComment> findByBoardId(Long boardId);

    void deleteByBoardId(Long boardId);
}
