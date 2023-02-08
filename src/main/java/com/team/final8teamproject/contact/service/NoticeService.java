package com.team.final8teamproject.contact.service;

import com.team.final8teamproject.contact.dto.NoticeRequest;
import com.team.final8teamproject.contact.entity.Notice;
import org.springframework.transaction.annotation.Transactional;

public interface NoticeService {
  void saveNotice(NoticeRequest noticeRequest, Long managerId);
  //todo 조회기능 페이징
  void updateNotice(Long id, Long managerId, NoticeRequest noticeRequest);
  void deleteNotice(Long id, Long managerId);
}
