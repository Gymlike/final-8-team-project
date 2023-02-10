package com.team.final8teamproject.contact.Comment.repository;

import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactCommentRepository extends JpaRepository<ContactComment,Long>{

  void deleteByUsername(String username);

  List<ContactComment> findAllByInquiryId(Long inquiryId);
}
