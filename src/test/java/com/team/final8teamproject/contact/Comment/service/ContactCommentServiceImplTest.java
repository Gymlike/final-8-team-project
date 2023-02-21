package com.team.final8teamproject.contact.Comment.service;

import com.team.final8teamproject.contact.Comment.repository.ContactCommentRepository;
import com.team.final8teamproject.security.jwt.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContactCommentServiceImplTest {
  @Mock
  private ContactCommentRepository contactCommentRepository;

  @InjectMocks
  private ContactCommentServiceImpl contactCommentService;

  @Spy
  private JwtUtil jwtUtil;


  @Test
  @DisplayName("댓글 등록 - 부모댓글 x , 댓글 등록")
  void saveInquiryComment() {

  }
}