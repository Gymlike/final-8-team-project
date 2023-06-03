package com.team.final8teamproject.contact.Repository;

import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.contact.entity.Notice;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InquiryRepository extends JpaRepository<Inquiry,Long> {


 // Page<Inquiry> findAllByTitleContainingOrContentContaining(String title, String content, PageRequest of);
  //IgnoreCase : 대소문자 구분없이 검색됨
 Page<Inquiry> findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content, PageRequest of);

// @Query("SELECT DISTINCT i FROM Inquiry i LEFT JOIN FETCH i.contactComments WHERE i.id = :inquiryId")
// Optional<Inquiry> findByIdWithComments(@Param("inquiryId") Long inquiryId);
}
