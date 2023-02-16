package com.team.final8teamproject.contact.Repository;

import com.team.final8teamproject.contact.entity.Notice;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {


  Page<Notice> findAllByTitleContainingOrContentContaining(String title, String content, PageRequest of);

  List<Notice> findAllByTitleContainingOrContentContaining(String title, String content);
}
