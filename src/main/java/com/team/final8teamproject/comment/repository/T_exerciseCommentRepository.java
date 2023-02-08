package com.team.final8teamproject.comment.repository;

import com.team.final8teamproject.comment.entity.T_exerciseComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface T_exerciseCommentRepository extends JpaRepository<T_exerciseComment,Long> {

    List<T_exerciseComment> findByUsername(String username);
}
