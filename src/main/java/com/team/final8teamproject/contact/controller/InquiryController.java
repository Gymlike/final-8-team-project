package com.team.final8teamproject.contact.controller;


import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.InquiryResponse;
import com.team.final8teamproject.contact.dto.UpdateInquiryRequest;
import com.team.final8teamproject.contact.service.InquiryService;
import com.team.final8teamproject.contact.service.InquiryServiceImpl.Result;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.user.service.UserService;
import jakarta.validation.Valid;
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

/** 웹컨피그.requestMatchers("/api/contact/inquiries/**").permitAll()
 * todo  메서드 마다 권한 설정
 * todo getWriterName  ->닉네임 가져와야 됨...... 우찌가져옴? 닉네임이어야 프론트 가능 .
 */
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class InquiryController {

  private final InquiryService inquiryService;
  private final UserService userService;

  @PostMapping("/users/contact/inquiries")
  public ResponseEntity createInquiry(@RequestBody @Valid InquiryRequest inquiryRequest,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    inquiryService.createInquiry(inquiryRequest,userDetails.getBase().getUsername(),userDetails.getBase().getNickName());
    return ResponseEntity.ok("등록 완료");
  }

  /** 모든 유저 조회 -> 비밀글 설정된 게시글 : 해당 유저 및 관리자만 볼 수 있도록
   * 웹에 리스트가 있다.
   * 내가 만약 유저가 아니면 -> 해당 글을 클릭 할 수 없다.
   * 내가 유저 또는 관리자라면 -> 글 을 볼 수 있다. 를 표현해야 한다.
   *
   * 프론트에서 처리 한다고 치자
   * 유저 아닐때와 유저
   */


  @GetMapping("/contact/inquiries")
  public Result getInquiry(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "direction", required = false, defaultValue = "DESC") Direction direction,
      @RequestParam(value = "properties", required = false, defaultValue = "createdDate") String properties) {
    return inquiryService.getInquiry(page,size,direction,properties);
  }

  @GetMapping("/contact/inquiries/{id}")
  public InquiryResponse getSelectedInquiry(
      @PathVariable Long  id,
      @AuthenticationPrincipal UserDetailsImpl userDetails){
    return inquiryService.getSelectedInquiry(id,userDetails.getBase().getNickName(),userDetails.getBase().getRole());
  }



  @GetMapping("/contact/inquiries/search")
  public Result searchByKeyword(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "direction", required = false, defaultValue = "DESC") Direction direction,
      @RequestParam(value = "properties", required = false, defaultValue = "createdDate") String properties) {
    return inquiryService.searchByKeyword(keyword, page, size, direction, properties);
  }

  //todo 모든 put매핑에 , 입력값만 최신화 되도록 하기 patch 안됨
  @PutMapping("/users/contact/inquiries/{id}")
  public ResponseEntity updateInquiry(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody UpdateInquiryRequest updateInquiryRequest){
    inquiryService.updateInquiry(id,userDetails.getBase().getUsername(), updateInquiryRequest);// todo getWriterName  ->닉네임 가져와야 됨...... 우찌가져옴? 닉네임이어야 프론트 가능 .

    return ResponseEntity.ok("수정 완료");
  }
  //todo 관리자가 유저 문의글 삭제 가능
  @DeleteMapping("/users/contact/inquiries/{id}")
  public ResponseEntity deleteInquiry(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails){
    inquiryService.deleteInquiry(id,userDetails.getBase().getUsername(),userDetails.getBase().getRole());
    return ResponseEntity.ok("삭제 완료");
  }



}
