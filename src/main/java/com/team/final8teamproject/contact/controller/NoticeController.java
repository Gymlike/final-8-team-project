package com.team.final8teamproject.contact.controller;

import com.team.final8teamproject.contact.dto.NoticeRequest;
import com.team.final8teamproject.contact.service.NoticeServiceImpl;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//todo 권한 : 관리자만  ( 조회 기능시 메서드별 권한 설정 요망)
@RequiredArgsConstructor
@RequestMapping("/api/managers/notice")
@RestController
public class NoticeController {

  //todo 페이징 처리 /,조회기능 /키워드검색
  private final NoticeServiceImpl noticeServiceImpl;

  //관리자 공지사항 등록
  @PostMapping("")
  public ResponseEntity saveNotice(@RequestBody NoticeRequest noticeRequest,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    noticeServiceImpl.saveNotice(noticeRequest, userDetails.getUser().getId());
    return ResponseEntity.ok("등록 완료");
    //new ResponseEntity<>("등록완료",HttpStatus.CREATED);
  }
//  @GetMapping("")
//  public


    // 관리자 공지사항 수정
    @PutMapping("/id")
    public ResponseEntity updateNotice(@PathVariable Long id,
        @AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody NoticeRequest noticeRequest) {
      noticeServiceImpl.updateNotice(id, userDetails.getUser().getId(), noticeRequest);
      return ResponseEntity.ok("수정 완료");
    }
      //관리자 공지사항 삭제
      @DeleteMapping("/id")
  public ResponseEntity deleteNotice(@PathVariable Long id,
          @AuthenticationPrincipal UserDetailsImpl userDetails){
      noticeServiceImpl.deleteNotice(id,userDetails.getUser().getId());
      return ResponseEntity.ok("삭제 완료");

  }

}
