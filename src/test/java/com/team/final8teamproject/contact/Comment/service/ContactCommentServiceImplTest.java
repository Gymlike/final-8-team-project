package com.team.final8teamproject.contact.Comment.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static reactor.core.publisher.Mono.when;

import com.team.final8teamproject.contact.Comment.dto.CreateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import com.team.final8teamproject.contact.Comment.repository.ContactCommentRepository;
import com.team.final8teamproject.contact.Repository.InquiryRepository;
import com.team.final8teamproject.contact.entity.Inquiry;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ContactCommentServiceImplTest {
  @Mock
  ContactCommentRepository contactCommentRepository;
  @Mock
  InquiryRepository inquiryRepository;

  @InjectMocks
  ContactCommentServiceImpl contactCommentService;

  private static ValidatorFactory factory;
  private static Validator validator;

  @BeforeAll
  public static void init() {
    factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @AfterAll
  public static void close() {
    factory.close();
  }

//  @Test
//  @DisplayName("부모댓글이 없는 경우 - 댓글 등록 성공 ")
//  void saveInquiryComment() {
//    //given
//    int depth = 1;
//    CreateContactCommentRequest createContactRequest = new CreateContactCommentRequest("comments",null);
//
//    Inquiry inquiry = new Inquiry("username", "nickname", "title", "content", false);
//   ContactComment contactComment =  createContactRequest.toEntity(anyLong(),"username","nickname",null,depth);
//    when(contactCommentRepository.save(contactComment))
//        .thenReturn(contactComment);
//    when(inquiryRepository.existsById(anyLong()))
//        .thenReturn(true);
//
//    //when
//    contactCommentService.saveInquiryComment(anyLong(),createContactRequest,"username","nickname");
//    //then
//    verify(contactCommentRepository).save(contactComment);
//  }

  @Test
  @DisplayName("contactComment 등록_실패 request에 @NotBlank 아닐때 ")
  void saveInquiryComment_fail() {
  }

  @Test
  @DisplayName("inquiry 등록 성공 ")
  void updateInquiryComment() {
  }

  @Test
  @DisplayName("inquiry 등록 성공 ")
  void deleteInquiryComment() {
  }

  @Test
  @DisplayName("inquiry 등록 성공 ")
  void deleteAllByInquiryId() {
  }
}