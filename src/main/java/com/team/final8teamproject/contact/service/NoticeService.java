package com.team.final8teamproject.contact.service;

import com.team.final8teamproject.contact.dto.NoticeRequest;
import com.team.final8teamproject.contact.dto.NoticeResponse;
import com.team.final8teamproject.contact.entity.Notice;
import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

public interface NoticeService {
  void saveNotice(NoticeRequest noticeRequest, Long managerId);
  //todo 조회기능 페이징
  void updateNotice(Long id, Long managerId, NoticeRequest noticeRequest);
  void deleteNotice(Long id, Long managerId);
  List<NoticeResponse> getNoticeList(int page, int size, Direction direction, String properties);

  NoticeResponse getSelectedNotice(Long id);
  List<NoticeResponse> searchByKeyword(String keyword, int page, int size, Direction direction, String properties);
}
