package com.team.final8teamproject.contact.contactComment.repository;

import com.team.final8teamproject.contact.contactComment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long>{
}
