package com.team.final8teamproject.contact.controller;


import com.team.final8teamproject.contact.dto.FaqRequest;
import com.team.final8teamproject.contact.dto.FaqResponse;
import com.team.final8teamproject.contact.service.FaqServiceImpl;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/faq")
@RestController
public class FaqController {

  private final FaqServiceImpl faqServiceImpl;

  //todo 권한 :관리자
  @PostMapping("")
  public ResponseEntity saveFaq(@RequestBody FaqRequest faqRequest,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    faqServiceImpl.saveFaq(faqRequest, userDetails.getUser().getId());
    return ResponseEntity.ok("등록 완료");
  }

  //todo 정렬기준 디폴트값 : direction적용되는지 확인,작성날짜  구현 이렇게 맞는지 확인하기!!
  @GetMapping("")
  public List<FaqResponse> getFaqList(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "direction", required = false, defaultValue = "desc") Direction direction,
      @RequestParam(value = "properties", required = false, defaultValue = "createAt") String properties) {
    return faqServiceImpl.getFaqList(page, size, direction, properties);
  }

  @GetMapping("/{id}")
  public FaqResponse getSelectedFaq(@PathVariable Long id) {
    return faqServiceImpl.getSelectedFaq(id);
  }


  //todo 권한 :관리자
  @DeleteMapping("/{id}")
  public ResponseEntity deleteFaq(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    faqServiceImpl.deleteFaq(id, userDetails.getUser().getId());
    return ResponseEntity.ok("삭제 완료");
  }

  //todo 검색 기능  ( 제목, 내용  작성자필요한지!!?? 물어보기 우선 작성자는 미구현 )
  @GetMapping("/keyword")
  public List<FaqResponse> searchByKeyword(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "direction", required = false, defaultValue = "DESC") Direction direction,
      @RequestParam(value = "properties", required = false, defaultValue = "createAt") String properties) {
    return faqServiceImpl.searchByKeyword(keyword,page,size,direction,properties);

  }

}
