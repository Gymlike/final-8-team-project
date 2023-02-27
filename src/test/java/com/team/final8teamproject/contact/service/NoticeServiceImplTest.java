//package com.team.final8teamproject.contact.service;
//
//
//import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.team.final8teamproject.contact.Repository.NoticeRepository;
//import com.team.final8teamproject.contact.entity.Notice;
//import java.util.List;
//import org.junit.After;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.junit.runner.RunWith;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class NoticeServiceImplTest {
//
//  @Autowired
//  NoticeRepository noticeRepository;
//
//  @After
//  public void cleanUp(){
//    noticeRepository.deleteAll();
//  }
//
//  @Test
//  @DisplayName("공지사항 등록 - 성공 ")
//  void saveNotice() {
//
//
//  }
//
//  @Test
//  @DisplayName("공지사항 전체조회 - 성공")
//  void getNoticeList() {
//    //given
//    String title = " 공지 테스트 ";
//    String content = "공지 본문" ;
//    Long manageId = 1L;
//    noticeRepository.save(Notice.builder().title(title).content(content).managerId(1L).build());
//    //when
//    List<Notice> noticeList = noticeRepository.findAll();
//
//    //then
//    Notice notice = noticeList.get(0);
//    assertThat(notice.getTitle()).isEqualTo(title);
//    assertThat(notice.getContent()).isEqualTo(content);
//  }
//
//  @Test
//  void getSelectedNotice() {
//  }
//
//  @Test
//  void searchByKeyword() {
//  }
//
//  @Test
//  void updateNotice() {
//  }
//
//  @Test
//  void deleteNotice() {
//  }
//}