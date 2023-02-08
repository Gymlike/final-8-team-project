package com.team.final8teamproject.contact.service;


import com.team.final8teamproject.contact.Repository.NoticeRepository;
import com.team.final8teamproject.contact.dto.NoticeRequest;
import com.team.final8teamproject.contact.entity.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class NoticeServiceImpl {

  private final NoticeRepository noticeRepository;


  //관리자만 공지사항 등록 가능
  @Transactional
  public void saveNotice(NoticeRequest noticeRequest, Long managerId) {
    Notice notice = noticeRequest.toEntity(managerId);
    noticeRepository.save(notice);
  }

 //todo 조회기능 페이징
  @Transactional
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
  public void deleteNotice(Long id, Long managerId) {
    Notice notice = noticeRepository.findById(id).orElseThrow(
        ()-> new IllegalArgumentException("해당 공지사항이 존재하지 않습니다.")
    );
    if(notice.getManagerId().equals(managerId)){
      noticeRepository.delete(notice);
    } else {
      throw new IllegalArgumentException("접근 할 수 있는 권한이 없습니다.");
    }
  }
}
