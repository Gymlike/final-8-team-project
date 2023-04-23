package com.team.final8teamproject.contact.Repository;

import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.contact.entity.Notice;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry,Long> {


 // Page<Inquiry> findAllByTitleContainingOrContentContaining(String title, String content, PageRequest of);
  //IgnoreCase : 대소문자 구분없이 검색됨
 Page<Inquiry> findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content, PageRequest of);

}
