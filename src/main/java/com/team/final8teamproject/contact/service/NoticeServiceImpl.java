package com.team.final8teamproject.contact.service;


import com.team.final8teamproject.contact.Repository.NoticeRepository;
import com.team.final8teamproject.contact.dto.NoticeRequest;
import com.team.final8teamproject.contact.dto.NoticeResponse;
import com.team.final8teamproject.contact.dto.UpdateNoticeRequest;
import com.team.final8teamproject.contact.entity.Notice;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

  private final NoticeRepository noticeRepository;


  //todo 관리자만 공지사항 등록 가능
  @Transactional
  @Override
  public void saveNotice(NoticeRequest noticeRequest, Long managerId) {
    Notice notice = noticeRequest.toEntity(managerId);
    noticeRepository.save(notice);
  }

  @Transactional(readOnly = true)
  @Override
  public List<NoticeResponse> getNoticeList(int page, int size, Direction direction,
      String properties) {
    Page<Notice> noticeListPage = noticeRepository.findAll(
        PageRequest.of(page, size, direction, properties));
    List<NoticeResponse> noticeResponses = noticeListPage.stream().map(NoticeResponse::new)
        .toList();
    return noticeResponses;
  }

  @Transactional(readOnly = true)
  @Override
  public NoticeResponse getSelectedNotice(Long id) {
    Notice notice = noticeRepository.findById(id).orElseThrow(
        () -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST)
    );
    return new NoticeResponse(notice);
  }

  @Transactional(readOnly = true)
  @Override
  public List<NoticeResponse> searchByKeyword(String keyword, int page, int size,
      Direction direction, String properties) {
    String title = keyword;
    String content = keyword;
    Page<Notice> noticeListPage = noticeRepository.findAllByTitleContainingOrContentContaining(
        title, content,
        PageRequest.of(page - 1, size, direction, properties));
    List<NoticeResponse> noticeResponses = noticeListPage.stream().map(NoticeResponse::new)
        .toList();
    return noticeResponses;
  }

  @Transactional
  @Override
  public void updateNotice(Long id, Long managerId, UpdateNoticeRequest updateNoticeRequest) {
    String title = updateNoticeRequest.getTitle();
    String content = updateNoticeRequest.getContent();

    Notice notice = noticeRepository.findById(id).orElseThrow(
        () -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST)
    );
    if (notice.getManagerId().equals(managerId)) {
      notice.update(title, content);
      noticeRepository.save(notice);
    } else {
      throw new CustomException(ExceptionStatus.ACCESS_DENINED);
    }

  }

  @Transactional
  @Override
  public void deleteNotice(Long id, Long managerId) {
    Notice notice = noticeRepository.findById(id).orElseThrow(
        () -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST)
    );
    if (notice.getManagerId().equals(managerId)) {
      noticeRepository.delete(notice);
    } else {
      throw new CustomException(ExceptionStatus.ACCESS_DENINED);
    }

  }
}
