package com.team.final8teamproject.contact.service;

import static org.assertj.core.api.Assertions.*;

import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import com.team.final8teamproject.contact.Comment.service.ContactCommentServiceImpl;
import com.team.final8teamproject.contact.Repository.InquiryRepository;
import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.InquiryResponse;
import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

@ExtendWith(MockitoExtension.class)
class InquiryServiceImplTest {

  @Mock
  InquiryRepository inquiryRepository;
  @Mock
  ContactCommentServiceImpl contactCommentService;

  @InjectMocks
  InquiryServiceImpl inquiryServiceImpl;

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

  @Test
  @DisplayName("inquiry 등록 성공 ")
  void createInquiry_success() {
    //given
    InquiryRequest inquiryRequest = InquiryRequest.builder()
        .title("title")
        .content("content")
        .secret(false)
        .build();
    when(inquiryRepository.save(any(Inquiry.class)))
        .thenReturn(inquiryRequest.toEntity("username", "nickname"));
    //when
    inquiryServiceImpl.createInquiry(inquiryRequest, "member1", "member1234");
    //then
    verify(inquiryRepository).save(any(Inquiry.class));
  }

  @Test
  @DisplayName("inquiry 등록_실패 request에 @NotBlank 아닐때 ")
  void createInquiry_fail() {
    //given
    InquiryRequest inquiryRequest = InquiryRequest.builder()
        .title("title")
        .content("content")
        .secret(false)
        .build();
    //when
    Set<ConstraintViolation<InquiryRequest>> violation = validator.validate(inquiryRequest);
    //then
    assertThat(violation).isEmpty();
    violation
        .forEach(error -> {
          assertThat(error.getMessage()).isEqualTo("공백일 수 없습니다");
        });
  }


  @Test
  @DisplayName("inquiry 전체조회_글이 없을때 예외발생")
  void getInquiry_throw() {
    //given
    int page = 1;
    int size = 10;
    Direction direction = Direction.DESC;
    String properties = "createdDate";
    when(inquiryRepository.findAll(PageRequest.of(page - 1, size, direction, properties)))
        .thenReturn(Page.empty());
    //when&then
    assertThatThrownBy(
        () -> inquiryServiceImpl.getInquiry(page, size, direction, properties)).isInstanceOf(
        CustomException.class);

  }

  @Test
  @DisplayName("Inquiry 건당조회 & getSecret_false 일때 성공")
  void getSelectedInquiry_false_success() {
    //given
    Inquiry inquiry = new Inquiry("username", "nickname", "title", "content", false);
    List<ContactComment> parentComments = contactCommentService.findAllByInquiryIdAndParentIsNull(
        anyLong());

    when(inquiryRepository.findById(1L))
        .thenReturn(Optional.of(inquiry));
    InquiryResponse inquiryResponse = new InquiryResponse(inquiry, parentComments);
    //when
    InquiryResponse response = inquiryServiceImpl.getSelectedInquiry(1L, "nickname",
        UserRoleEnum.MEMBER);
    //then
    assertThat(response.getNickName()).isEqualTo(inquiryResponse.getNickName());
    assertThat(response.getTitle()).isEqualTo(inquiryResponse.getTitle());
    assertThat(response.getContent()).isEqualTo(inquiryResponse.getContent());
    assertThat(response.getComments()).isEqualTo(inquiryResponse.getComments());
    verify(inquiryRepository).findById(anyLong());
  }

//  @DisplayName("Inquiry 건당조회 & getSecret_true 일때 해당유저만 보기 성공")
//  void getSelectedInquiry_true_validUser_success () {
//    User user = new User("username","username1234",UserRoleEnum.MEMBER,"validUser","01022223333","member@naver.com",0L);
//    Inquiry inquiry = new Inquiry("username", "validUser", "title", "content", true);
//    when(inquiryRepository.findById(1L))
//        .thenReturn(Optional.of(inquiry));
//
//    if (inquiry.isNickName(user.getNickName()) || user.getRole().equals(UserRoleEnum.MANAGER)) {
//      List<ContactComment> parentComments = contactCommentService.findAllByInquiryIdAndParentIsNull(
//          anyLong());
//    }
//
//    InquiryResponse inquiryResponse = new InquiryResponse(inquiry, parentComments);
//
//    //when
//    InquiryResponse response = inquiryServiceImpl.getSelectedInquiry(1L, "validUser",
//        UserRoleEnum.MEMBER);
//    //then
//    assertThat(response.getNickName()).isEqualTo(inquiryResponse.getNickName());
//    assertThat(response.getTitle()).isEqualTo(inquiryResponse.getTitle());
//    assertThat(response.getContent()).isEqualTo(inquiryResponse.getContent());
//    assertThat(response.getComments()).isEqualTo(inquiryResponse.getComments());
//  }


    @Test
    @DisplayName("Inquiry 건당조회 & getSecret_true 일때 관리자일때 보기 성공")
    void getSelectedInquiry_true_Role_Manager_success () {
      //      if (inquiry.isNickName(nickName) || role.equals(UserRoleEnum.MANAGER)) {
      //        List<ContactComment> parentComments = contactCommentService.findAllByInquiryIdAndParentIsNull(
      //            id);
      //        return new InquiryResponse(inquiry, parentComments);
      //given

      //when
      InquiryResponse response = inquiryServiceImpl.getSelectedInquiry(1L, "nickname",
          UserRoleEnum.MEMBER);
      //then

    }

    @Test
    @DisplayName("Inquiry 건당조회 & getSecret_true 일때 예외")
    void getSelectedInquiry_true_throw () {
//        throw new CustomException(ExceptionStatus.SECRET_POST);
    }
    @Test
    //todo 물어보기 😄😄😄😄😄😄😄😄
    @DisplayName("Inquiry 건당조회 & getSecret_false 일때 실패_댓글 못가져오는 경우???")
    void getSelectedInquiry_fail () {

    }

    @Test
    void searchByKeyword () {
    }
    @Test
    void updateInquiry () {
    }
    @Test
    void deleteInquiry () {
    }

    @Test
    void findById () {
    }
  }