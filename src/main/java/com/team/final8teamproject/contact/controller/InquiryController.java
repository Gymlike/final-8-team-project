package com.team.final8teamproject.contact.controller;


import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.InquiryResponse;
import com.team.final8teamproject.contact.service.InquiryServiceImpl;
import com.team.final8teamproject.security.userservice.UserDetailsImpl;
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


@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class InquiryController {// todo  메서드 마다 권한 설정

  private final InquiryServiceImpl inquiryServiceImpl;

  @PostMapping("/user/contact/inquiry")
  public ResponseEntity createInquiry(@RequestBody InquiryRequest inquiryRequest,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    inquiryServiceImpl.createInquiry(inquiryRequest, userDetails.getBase().getUsername());
    return ResponseEntity.ok("등록 완료");
  }

 // todo 풀받은 후  웹컨피그 . permitAll()/api/contact/inquiry/**
  @GetMapping("/contact/inquiry")
  public List<InquiryResponse>  getInquiry(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "direction", required = false, defaultValue = "desc") Direction direction,
      @RequestParam(value = "properties", required = false, defaultValue = "createdDate") String properties) {
    return inquiryServiceImpl.getInquiry(page,size,direction,properties);
  }

  @GetMapping("/contact/inquiry/{id}")
  public InquiryResponse getSelectedInquiry(@PathVariable Long  id){
    return inquiryServiceImpl.getSelectedInquiry(id);
  }



  @GetMapping("/contact/inquiry/keyword")
  public List<InquiryResponse> searchByKeyword(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "direction", required = false, defaultValue = "DESC") Direction direction,
      @RequestParam(value = "properties", required = false, defaultValue = "createdDate") String properties) {
    return inquiryServiceImpl.searchByKeyword(keyword, page, size, direction, properties);
  }

  //todo 모든 put매핑에 , 입력값만 최신화 되도록 하기 patch 안됨
  @PutMapping("/user/contact/inquiry/{id}")
  public ResponseEntity updateInquiry(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody InquiryRequest inquiryRequest){
    inquiryServiceImpl.updateInquiry(id,userDetails.getBase().getUsername(),inquiryRequest);
    return ResponseEntity.ok("수정 완료");
  }
  //todo 관리자가 유저 문의글 삭제 가능
  @DeleteMapping("/user/contact/inquiry/{id}")
  public ResponseEntity deleteInquiry(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails){
    inquiryServiceImpl.deleteInquiry(id,userDetails.getBase().getUsername());
    return ResponseEntity.ok("삭제 완료");
  }
  //todo 권한 : 관리자만
  // 관리자의 문의사항 삭제 기능
  @DeleteMapping("/manager/contact/inquiry/{id}")
  public ResponseEntity deleteManager(@PathVariable Long id){
    inquiryServiceImpl.deleteManager(id);
    return ResponseEntity.ok("삭제 완료");
  }


}
