package com.team.final8teamproject.board.repository;

import com.team.final8teamproject.board.entity.T_exercise;
import com.team.final8teamproject.board.entity.TodayMeal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodayMealRepository extends JpaRepository<TodayMeal,Long> {
    List<T_exercise> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(
            String title,
            String content,
            Pageable pageable
    );
}
