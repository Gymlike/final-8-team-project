package com.team.final8teamproject.user.repository;

import com.team.final8teamproject.user.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
