package com.team.final8teamproject.contact.Comment.controller;

import com.team.final8teamproject.contact.Comment.dto.CreateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.dto.UpdateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.service.ContactCommentServiceImpl;
import com.team.final8teamproject.security.service.UserDetailsImpl;

import com.team.final8teamproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo  관리자,총관리자, 작성한 유저(유저,사업자) 댓글 등록 ,수정,삭제
 * todo getWriterName  ->닉네임 가져와야 됨...... 우찌가져옴? 닉네임이어야 프론트 가능 .
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class ContactCommentController {

  private final ContactCommentServiceImpl contactCommentServiceIml;//todo 추후 개방패쇄원칙 인터페이스로 주입하기 그럼 자동으로 주입이됨.
  private final UserService userService;

  @PostMapping("/inquiry/{id}")
  public ResponseEntity savaInquiryComment(
      @PathVariable Long id,
      @RequestBody CreateContactCommentRequest createContactCommentRequest,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    System.out.println(createContactCommentRequest.getComments());
    contactCommentServiceIml.saveInquiryComment(id, createContactCommentRequest,
        userDetails.getBase().getUsername(),userDetails.getBase().getNickName());
    return ResponseEntity.ok("등록 완료");
  }


  //댓글 수정 todo patch타입으로 사용해 일부수정해보기
  @PutMapping("/{id}/inquiry")
  public ResponseEntity updateInquiryComment(
      @PathVariable Long id,
      @RequestBody UpdateContactCommentRequest updateCommentRequest,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    contactCommentServiceIml.updateInquiryComment(id,
        updateCommentRequest, userDetails.getBase().getUsername());
    return ResponseEntity.ok("수정 완료");
  }

// todo 댓글 페이징 처리 필요 하지 않을까?? - 댓글이 수십만개라도 했을때 포스트 불러올때 로딩 지연 우려  음... 고민해보자 .
  @DeleteMapping("/{id}/inquiry")
  public ResponseEntity deleteInquiryComment(
      @PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    contactCommentServiceIml.deleteInquiryComment(id, userDetails.getBase().getUsername(),userDetails.getBase().getRole());
    return ResponseEntity.ok("삭제 완료");
  }



}
