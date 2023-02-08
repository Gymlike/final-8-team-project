package com.team.final8teamproject.contact.Repository;

import com.team.final8teamproject.contact.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {


  Page<Notice> findAllBySearch(String title, String content, PageRequest of);
}
