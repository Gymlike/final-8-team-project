package com.team.final8teamproject.contact.service;


import com.team.final8teamproject.contact.Repository.NoticeRepository;
import com.team.final8teamproject.contact.dto.NoticeRequest;
import com.team.final8teamproject.contact.entity.Notice;
import com.team.final8teamproject.manager.entity.Manager;
import com.team.final8teamproject.manager.entity.ManagerRepository;
import com.team.final8teamproject.user.entity.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class NoticeService {
  private final NoticeRepository noticeRepository;
  private final ManagerRepository managerRepository;


  //관리자만 공지사항 등록 가능
  @Transactional(readOnly = true)
  public void saveNotice(NoticeRequest noticeRequest, Long managerId) {
    Manager manager =managerRepository.findById(managerId).orElseThrow(
        ()-> new IllegalArgumentException("해당 관리자가 존재하지 않습니다. ")
    );
   Notice notice = noticeRequest.toEntity();
   noticeRepository.save(notice);
  }


}
