package com.team.final8teamproject.contact.service;


import com.team.final8teamproject.contact.Repository.NoticeRepository;
//import com.team.final8teamproject.contact.dto.InquiryResponse;
import com.team.final8teamproject.contact.dto.NoticeRequest;
import com.team.final8teamproject.contact.dto.NoticeResponse;
//import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.contact.entity.Notice;
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
        () -> new IllegalArgumentException("해당 공지사항이 존재하지 않습니다.")
    );
    return new NoticeResponse(notice);
  }

  @Transactional(readOnly = true)
  @Override
  public List<NoticeResponse> searchByKeyword(String keyword, int page, int size,
      Direction direction, String properties) {
    String title = keyword;
    String content = keyword;
    Page<Notice> noticeListPage = noticeRepository.findAllByTitleContainingOrContentContaining(title, content,
        PageRequest.of(page - 1, size, direction, properties));
    List<NoticeResponse> noticeResponses = noticeListPage.stream().map(NoticeResponse::new)
        .toList();
    return noticeResponses;
  }

  @Transactional
  @Override
  public void updateNotice(Long id, Long managerId, NoticeRequest noticeRequest) {
    Notice notice = noticeRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 공지사항이 존재하지 않습니다.")
    );
    if (notice.getManagerId().equals(managerId)) {
      notice.update(noticeRequest);
      noticeRepository.save(notice);
    } else {
      throw new IllegalArgumentException("접근 할 수 있는 권한이 없습니다.");
    }

  }

  @Transactional
  @Override
  public void deleteNotice(Long id, Long managerId) {
    Notice notice = noticeRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 공지사항이 존재하지 않습니다.")
    );
    if (notice.getManagerId().equals(managerId)) {
      noticeRepository.delete(notice);
    } else {
      throw new IllegalArgumentException("접근 할 수 있는 권한이 없습니다.");
    }
  }

}
