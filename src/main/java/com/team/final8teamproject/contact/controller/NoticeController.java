package com.team.final8teamproject.contact.controller;

//import com.team.final8teamproject.contact.dto.InquiryResponse;
import com.team.final8teamproject.contact.dto.NoticeRequest;
import com.team.final8teamproject.contact.dto.NoticeResponse;
import com.team.final8teamproject.contact.dto.UpdateNoticeRequest;
import com.team.final8teamproject.contact.service.NoticeServiceImpl;
import com.team.final8teamproject.contact.service.NoticeServiceImpl.Result;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//todo 권한 : 관리자만  ( 조회 기능시 메서드별 권한 설정 요망)
@RequiredArgsConstructor
@RequestMapping("/api/managers/notices")
@RestController
public class NoticeController {

  /** 웹컨피그.requestMatchers("/api/faqs/check/**").permitAll()
   * todo  메서드 마다 권한 설정
   */
  private final NoticeServiceImpl noticeServiceImpl;

  //관리자 공지사항 등록
  @PostMapping("")
  public ResponseEntity saveNotice(@RequestBody NoticeRequest noticeRequest,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    noticeServiceImpl.saveNotice(noticeRequest, userDetails.getUser().getId());
    return ResponseEntity.ok("등록 완료");
    //new ResponseEntity<>("등록완료",HttpStatus.CREATED);
  }


  //todo 페이징 처리 /,조회기능 /키워드검색 api/managers/notices/check/**
  @GetMapping("/check")
  public Result getNoticeList(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "2") int size,
      @RequestParam(value = "direction", required = false, defaultValue = "desc") Direction direction,
      @RequestParam(value = "properties", required = false, defaultValue = "createdDate") String properties) {
    return noticeServiceImpl.getNoticeList(page, size, direction, properties);
  }

  @GetMapping("/check/{id}")
  public NoticeResponse getSelectedNotice(@PathVariable Long id) {
    return noticeServiceImpl.getSelectedNotice(id);
  }

  @GetMapping("/check/keywords")
  public Result searchByKeyword(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "2") int size,
      @RequestParam(value = "direction", required = false, defaultValue = "DESC") Direction direction,
      @RequestParam(value = "properties", required = false, defaultValue = "createdDate") String properties) {
    return noticeServiceImpl.searchByKeyword(keyword, page, size, direction, properties);

  }
  // 관리자 공지사항 수정
  @PutMapping("/{id}")
  public ResponseEntity updateNotice(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody UpdateNoticeRequest updateNoticeRequest) {
    noticeServiceImpl.updateNotice(id, userDetails.getUser().getId(),updateNoticeRequest);
    return ResponseEntity.ok("수정 완료");
  }


  //관리자 공지사항 삭제
  @DeleteMapping("/{id}")
  public ResponseEntity deleteNotice(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    noticeServiceImpl.deleteNotice(id, userDetails.getUser().getId());
    return ResponseEntity.ok("삭제 완료");

  }

}
