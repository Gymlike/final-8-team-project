package com.team.final8teamproject.board.repository;

import com.team.final8teamproject.board.entity.T_exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface T_exerciseRepository extends JpaRepository<T_exercise,Long> {
    Page<T_exercise> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(
            String title,
            String content,
            Pageable pageable
    );

 List<Long> findIdByCreatedDateString(String dateTime);

}
