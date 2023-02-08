package com.team.final8teamproject.contact.controller;


import com.team.final8teamproject.contact.dto.FaqResponse;
import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.InquiryResponse;
import com.team.final8teamproject.contact.service.InquiryServiceImpl;
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

//문의하기
//1. 회원(유저), 사업자 - 문의글 등록 / 수정 /삭제
//2. 관리자 - 문의 답변 등록/ 수정 /삭제 / 고객문의글 삭제 기능
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class InquiryController {

  //todo 페이징 처리  // 키워드 검색?
  private final InquiryServiceImpl inquiryServiceImpl;

  @PostMapping("/user/contact/inquiry")
  public ResponseEntity createInquiry(@RequestBody InquiryRequest inquiryRequest,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    inquiryServiceImpl.createInquiry(inquiryRequest, userDetails.getUser().getId());
    return ResponseEntity.ok("등록 완료");
  }
 //todo 조회는 관리자, 유저, 사업자 다 가능
  @GetMapping("/contact/inquiry")
  public List<InquiryResponse>  getInquiry(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "direction", required = false, defaultValue = "desc") Direction direction,
      @RequestParam(value = "properties", required = false, defaultValue = "createAt") String properties) {
    return inquiryServiceImpl.getInquiry(page,size,direction,properties);
  }



  @PutMapping("/user/contact/inquiry/{id}")
  public ResponseEntity updateInquiry(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody InquiryRequest inquiryRequest){
    inquiryServiceImpl.updateInquiry(id,userDetails.getUser().getId(),inquiryRequest);
    return ResponseEntity.ok("수정 완료");
  }
  //todo 관리자가 유저 문의글 삭제 가능
  @DeleteMapping("/user/contact/inquiry/{id}")
  public ResponseEntity deleteInquiry(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails){
    inquiryServiceImpl.deleteInquiry(id,userDetails.getUser().getId());
    return ResponseEntity.ok("삭제 완료");
  }
  //권한!
//  @DeleteMapping("/manager/contact/inquiry/{id}")
//  public ResponseEntity deleteInquiryM(@PathVariable Long id,
//      @AuthenticationPrincipal UserDetailsImpl userDetails){
//    inquiryServiceImpl.deleteManger(id,userDetails.getUser().getId());
//    return ResponseEntity.ok("삭제 완료");
//  }

  //todo  키워드 검색으로 조회
  @GetMapping("/contact/inquiry/")
  public List<InquiryResponse> searchByKeyword(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "direction", required = false, defaultValue = "DESC") Direction direction,
      @RequestParam(value = "properties", required = false, defaultValue = "createAt") String properties) {
    return inquiryServiceImpl.searchByKeyword(keyword, page, size, direction, properties);
  }

}
