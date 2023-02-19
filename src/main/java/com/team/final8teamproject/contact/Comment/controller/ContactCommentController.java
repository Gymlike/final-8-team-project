package com.team.final8teamproject.contact.Comment.controller;

import com.team.final8teamproject.contact.Comment.dto.ContactCommentResponse;
import com.team.final8teamproject.contact.Comment.dto.CreateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.dto.UpdateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.servive.ContactCommentServiceImpl;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import java.util.List;
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
/**
 * todo  관리자,총관리자, 작성한 유저(유저,사업자) 댓글 등록 ,수정,삭제
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class ContactCommentController {

  private final ContactCommentServiceImpl contactCommentServiceIml;

  @PostMapping("/inquiry/{id}")
  public ResponseEntity savaInquiryComment(
      @PathVariable Long id,
      @RequestBody CreateContactCommentRequest createContactCommentRequest,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    System.out.println(createContactCommentRequest.getComments());
    contactCommentServiceIml.saveInquiryComment(id, createContactCommentRequest,
        userDetails.getUser().getUsername(),userDetails.getUser().getNickName());
    return ResponseEntity.ok("등록 완료");
  }


  //댓글 수정
  @PutMapping("/{id}/inquiry")
  public ResponseEntity updateInquiryComment(
      @PathVariable Long id,
      @RequestBody UpdateContactCommentRequest updateCommentRequest,
      @AuthenticationPrincipal UserDetailsImpl userDetails){
    contactCommentServiceIml.updateInquiryComment(id,
        updateCommentRequest,userDetails.getUser().getUsername(),userDetails.getUser().getNickName());
    return ResponseEntity.ok("수정 완료");
  }

  //댓글 삭제
 @DeleteMapping("/{id}/inquiry")
  public ResponseEntity deleteInquiryComment(
      @PathVariable Long id,
     @AuthenticationPrincipal UserDetailsImpl userDetails){
    contactCommentServiceIml.deleteInquiryComment(id,userDetails.getUser().getUsername());
    return ResponseEntity.ok("삭제 완료");
 }

  //todo 권한 : 관리자만
  // 관리자의 댓글 삭제 기능
  @DeleteMapping("/{id}/inquiry/managers")
  public ResponseEntity deleteManager(@PathVariable Long id){
    contactCommentServiceIml.deleteManager(id);
    return ResponseEntity.ok("삭제 완료");
  }



}
