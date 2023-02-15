package com.team.final8teamproject.contact.controller;


import com.team.final8teamproject.contact.dto.FaqRequest;
import com.team.final8teamproject.contact.dto.FaqResponse;
import com.team.final8teamproject.contact.dto.UpdateFaqRequest;
import com.team.final8teamproject.contact.service.FaqServiceImpl;
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
@RequestMapping("/api/faq")
@RestController
public class FaqController {// todo  메서드 마다 권한 설정

  private final FaqServiceImpl faqServiceImpl;

  //todo 권한 :관리자
  @PostMapping("")
  public ResponseEntity saveFaq(@RequestBody FaqRequest faqRequest,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    faqServiceImpl.saveFaq(faqRequest, userDetails.getBase().getId());
    return ResponseEntity.ok("등록 완료");
  }

//todo 풀받은 후  웹컨피그 . permitAll()/api/faq/check/**
  @GetMapping("/check")
  public List<FaqResponse> getFaqList(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "direction", required = false, defaultValue = "desc") Direction direction,
      @RequestParam(value = "properties", required = false, defaultValue = "createdDate") String properties) {
    return faqServiceImpl.getFaqList(page, size, direction, properties);
  }

  @GetMapping("/check/{id}")
  public FaqResponse getSelectedFaq(@PathVariable Long id) {
    return faqServiceImpl.getSelectedFaq(id);
  }

  @GetMapping("/check/keyword")
  public List<FaqResponse> searchByKeyword(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "direction", required = false, defaultValue = "DESC") Direction direction,
      @RequestParam(value = "properties", required = false, defaultValue = "createdDate") String properties) {
    return faqServiceImpl.searchByKeyword(keyword,page,size,direction,properties);

  }

  @PutMapping("/{id}")
  public ResponseEntity updateFaq(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody UpdateFaqRequest updateFaqRequest){
    faqServiceImpl.updateFaq(id,userDetails.getBase().getId(),updateFaqRequest);
    return ResponseEntity.ok("수정 완료");
  }
  //todo 권한 :관리자
  @DeleteMapping("/{id}")
  public ResponseEntity deleteFaq(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    faqServiceImpl.deleteFaq(id, userDetails.getBase().getId());
    return ResponseEntity.ok("삭제 완료");
  }




}
