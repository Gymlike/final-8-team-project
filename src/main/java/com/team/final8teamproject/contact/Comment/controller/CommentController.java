package com.team.final8teamproject.contact.Comment.controller;

import com.team.final8teamproject.contact.Comment.dto.CommentRequest;
import com.team.final8teamproject.contact.Comment.servive.CommentServiceImpl;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

  //댓글 등록 .. 대댓글
  private final CommentServiceImpl commentServiceImpl;

  @PostMapping("/{inquiryId}")
  public ResponseEntity InquiryComment(
      @PathVariable Long inquiryId,
      @RequestBody CommentRequest commentRequest,  // comment, parentId;
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    commentServiceImpl.InquiryComment(commentRequest,inquiryId,userDetails.getUsername());
    return ResponseEntity.ok("등록 완료");
  }

  //댓글 수정

  //댓글 삭제
}
