package com.team.final8teamproject.contact.service;


import com.team.final8teamproject.contact.Repository.NoticeRepository;
import com.team.final8teamproject.contact.dto.NoticeRequest;
import com.team.final8teamproject.contact.entity.Notice;
import com.team.final8teamproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class NoticeService {
  private final NoticeRepository noticeRepository;
  private final com.team.final8teamproject.contact.entity.NoticeRepository noticeRepository;

  //관리자만 공지사항 등록 가능
  @Transactional(readOnly = true)
  public void saveNotice(NoticeRequest noticeRequest, Long userId) {
   Notice notice = new Notice(noticeRequest.toEntity(),userId);
   noticeRepository.save(notice);
  }


}
