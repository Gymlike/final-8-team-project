package com.team.final8teamproject.board.comment.repository;

import com.team.final8teamproject.board.comment.entity.T_exerciseComment;
import com.team.final8teamproject.board.comment.entity.TodayMealComment;
import com.team.final8teamproject.board.entity.TodayMeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodayMealCommentRepository extends JpaRepository<TodayMealComment,Long> {

//    List<T_exerciseComment> findByUsername(String username);
    List<TodayMealComment> findByBoardId(Long boardId);

    void deleteByBoardId(Long boardId);
}
