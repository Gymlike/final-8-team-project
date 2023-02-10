package com.team.final8teamproject.contact.Comment.controller;

import com.team.final8teamproject.contact.Comment.dto.CreateCommentRequest;
import com.team.final8teamproject.contact.Comment.dto.UpdateCommentRequest;
import com.team.final8teamproject.contact.Comment.servive.CommentServiceImpl;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import jakarta.validation.constraints.Pattern;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
//todo  관리자,총관리자, 작성한 유저(유저,사업자) 댓글 등록 ,수정,삭제


  private final CommentServiceImpl commentServiceImpl;

  @PostMapping("/inquiries/{id}")
  public ResponseEntity savaInquiryComment(
      @PathVariable Long id,
      @RequestBody CreateCommentRequest createCommentRequest,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    commentServiceImpl.saveInquiryComment(id, createCommentRequest,
        userDetails.getUser().getUsername());
    return ResponseEntity.ok("등록 완료");
  }

//  //댓글 수정
//  @PutMapping("/inquiry/{commentId}")
//  public ResponseEntity updateInquiryComment(
//      @PathVariable Long commentId,
//      @RequestBody UpdateCommentRequest updateCommentRequest,
//      @AuthenticationPrincipal UserDetailsImpl userDetails){
//    commentServiceImpl.updateInquiryComment(commentId,updateCommentRequest,userDetails.getUser().getUsername());
//    return ResponseEntity.ok("수정 완료");
//  }
//
//  //댓글 삭제
// @DeleteMapping("/inquiry/{commentId")
//  public ResponseEntity deleteInquiryComment(
//      @PathVariable Long commentId,
//     @AuthenticationPrincipal UserDetailsImpl userDetails){
//    commentServiceImpl.deleteInquiryComment(commentId,userDetails.getUser().getUsername());
//    return ResponseEntity.ok("삭제 완료");
// }


}
