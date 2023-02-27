package com.team.final8teamproject.contact.controller;


import com.sun.mail.imap.ResyncData;
import com.team.final8teamproject.contact.dto.FaqRequest;
import com.team.final8teamproject.contact.dto.FaqResponse;
import com.team.final8teamproject.contact.dto.UpdateFaqRequest;
import com.team.final8teamproject.contact.service.FaqService;
import com.team.final8teamproject.contact.service.FaqServiceImpl;
import com.team.final8teamproject.contact.service.FaqServiceImpl.Result;
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

/** 웹컨피그.requestMatchers("/api/faqs/check/**").permitAll()
 *  이미지 파일 ? 여러개 올리는 방법? 구현 할까말까
 * todo  메서드 마다 권한 설정
 */
@RequiredArgsConstructor
@RequestMapping("/api/faqs")
@RestController
public class FaqController {

  private final FaqService faqService;


  //todo 권한 :관리자
  @PostMapping("")
  public ResponseEntity saveFaq(@RequestBody FaqRequest faqRequest,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    faqService.saveFaq(faqRequest, userDetails.getBase().getId());
    return ResponseEntity.ok("등록 완료");
  }



  @GetMapping("/check")
  public Result getFaqList(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "2") int size,
      @RequestParam(value = "direction", required = false, defaultValue = "DESC") Direction direction,
      @RequestParam(value = "properties", required = false, defaultValue = "createdDate") String properties) {
    return faqService.getFaqList(page, size, direction, properties);
  }

  @GetMapping("/check/{id}")
  public FaqResponse getSelectedFaq(@PathVariable Long id) {
    return faqService.getSelectedFaq(id);
  }

  @GetMapping("/check/search")
  public Result searchByKeyword(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "2") int size,
      @RequestParam(value = "direction", required = false, defaultValue = "DESC") Direction direction,
      @RequestParam(value = "properties", required = false, defaultValue = "createdDate") String properties) {
    return faqService.searchByKeyword(keyword,page,size,direction,properties);

  }

  @PutMapping("/{id}")
  public ResponseEntity updateFaq(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody UpdateFaqRequest updateFaqRequest){
    faqService.updateFaq(id,userDetails.getBase().getId(),updateFaqRequest);
    return ResponseEntity.ok("수정 완료");
  }
  //todo 권한 :관리자
  @DeleteMapping("/{id}")
  public ResponseEntity deleteFaq(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    faqService.deleteFaq(id, userDetails.getBase().getId());
    return ResponseEntity.ok("삭제 완료");
  }




}
