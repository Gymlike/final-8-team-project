package com.team.final8teamproject.board.repository;

import com.team.final8teamproject.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
