package com.team.final8teamproject.contact.service;

import static org.junit.jupiter.api.Assertions.*;

import com.team.final8teamproject.contact.Repository.NoticeRepository;
import com.team.final8teamproject.security.jwt.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class NoticeServiceImplTest {
  @Mock
  private NoticeRepository noticeRepository;

  @InjectMocks
  private NoticeService noticeService;

  @Spy
  private JwtUtil jwtUtil;

  @Test
  @DisplayName("공지사항 등록 성공 ")
  void saveNotice_success() {
    //given

    //when

    //then
  }
  @Test
  @DisplayName("공지사항 등록 실패 ")
  void saveNotice_fail() {
  }

  @Test
  void getNoticeList() {
  }

  @Test
  void getSelectedNotice() {
  }

  @Test
  void searchByKeyword() {
  }

  @Test
  void updateNotice() {
  }

  @Test
  void deleteNotice() {
  }
}