package com.team.final8teamproject.contact.service;

import com.team.final8teamproject.contact.dto.NoticeRequest;
import com.team.final8teamproject.contact.dto.NoticeResponse;
import com.team.final8teamproject.contact.dto.UpdateNoticeRequest;
import com.team.final8teamproject.contact.entity.Notice;

import com.team.final8teamproject.contact.service.NoticeServiceImpl.Result;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

public interface NoticeService {
  void saveNotice(@Valid NoticeRequest noticeRequest, Long managerId,String imageUrl) ;
  void updateNotice(Long id, Long managerId, UpdateNoticeRequest updateNoticeRequest, String imageUrl);
  void deleteNotice(Long id, Long managerId);
  Result getNoticeList(int page, int size, Direction direction, String properties);

  NoticeResponse getSelectedNotice(Long id);
  Result searchByKeyword(String keyword, int page, int size, Direction direction, String properties);
}
