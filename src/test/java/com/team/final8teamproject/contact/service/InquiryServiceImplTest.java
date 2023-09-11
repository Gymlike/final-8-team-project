package com.team.final8teamproject.contact.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import com.team.final8teamproject.contact.Comment.service.ContactCommentServiceImpl;
import com.team.final8teamproject.contact.Repository.InquiryRepository;
import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.InquiryResponse;
import com.team.final8teamproject.contact.dto.UpdateInquiryRequest;
import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.contact.service.InquiryServiceImpl.Result;
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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
          assertThat(error.getMessage()).isEqualTo("This is not blank");
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
    InquiryResponse inquiryResponse = new InquiryResponse(inquiry);
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

  @Test
  @DisplayName("Inquiry 건당조회 & getSecret_true 일때 해당유저만 보기 성공")
  void getSelectedInquiry_true_validUser_success() {
    InquiryResponse inquiryResponse = null;
    User user = new User("username", "username1234", UserRoleEnum.MEMBER, "validUser",
        "01022223333", "member@naver.com");
    Inquiry inquiry = new Inquiry("username", "validUser", "title", "content", true);
    when(inquiryRepository.findById(1L))
        .thenReturn(Optional.of(inquiry));

      List<ContactComment> parentComments = contactCommentService.findAllByInquiryIdAndParentIsNull(
        anyLong());
     inquiryResponse = new InquiryResponse(inquiry);


    //when
    InquiryResponse response = inquiryServiceImpl.getSelectedInquiry(1L, "validUser",
        UserRoleEnum.MEMBER);
    //then
    assertThat(response.getNickName()).isEqualTo(inquiryResponse.getNickName());
    assertThat(response.getTitle()).isEqualTo(inquiryResponse.getTitle());
    assertThat(response.getContent()).isEqualTo(inquiryResponse.getContent());
    assertThat(response.getComments()).isEqualTo(inquiryResponse.getComments());
    verify(inquiryRepository).findById(anyLong());
  }




  @Test
  @DisplayName("Inquiry 건당조회 & getSecret_true 일때 관리자일때 보기 성공")
  void getSelectedInquiry_true_Role_Manager_success() {
    //given
    InquiryResponse inquiryResponse = null;
    User user = new User("manager", "username1234", UserRoleEnum.MANAGER, "manager",
        "01022223333", "member@naver.com");
    Inquiry inquiry = new Inquiry("username", "invalidUser", "title", "content", true);
    when(inquiryRepository.findById(1L))
        .thenReturn(Optional.of(inquiry));
      List<ContactComment> parentComments = contactCommentService.findAllByInquiryIdAndParentIsNull(
          anyLong());
      inquiryResponse = new InquiryResponse(inquiry);

    //when
    InquiryResponse response = inquiryServiceImpl.getSelectedInquiry(1L, "invalidUser",
        UserRoleEnum.MEMBER);
    //then
    assertThat(response.getNickName()).isEqualTo(inquiryResponse.getNickName());
    assertThat(response.getTitle()).isEqualTo(inquiryResponse.getTitle());
    assertThat(response.getContent()).isEqualTo(inquiryResponse.getContent());
    assertThat(response.getComments()).isEqualTo(inquiryResponse.getComments());
  }


  @Test
  @DisplayName("Inquiry 건당조회 & getSecret_true 일때 관리자도 아니고 해당 유저도 아닐때 보기 예외")
  void getSelectedInquiry_true_invalidUserAndRole_MemBer_throw() {
    //given
    InquiryResponse inquiryResponse = null;
    User user = new User("member1", "username1234", UserRoleEnum.MEMBER, "invalidUser",
        "01022223333", "member@naver.com");
    Inquiry inquiry = new Inquiry("username", "validUser", "title", "content", true);
    when(inquiryRepository.findById(1L))
        .thenReturn(Optional.of(inquiry));
      List<ContactComment> parentComments = contactCommentService.findAllByInquiryIdAndParentIsNull(
          anyLong());
      inquiryResponse = new InquiryResponse(inquiry);


    //when&then
    assertThatThrownBy(() ->
        inquiryServiceImpl.getSelectedInquiry(1L, "invalidUser",
            UserRoleEnum.MEMBER)).isInstanceOf(CustomException.class);
  }


  @Test
  @DisplayName("inquiry 수정_해당 유저 일때 성공")
  void updateInquiry_valiedUser_success() {
    //given
    Inquiry inquiry = new Inquiry("username", "nickname", "title", "content", false);
    UpdateInquiryRequest updateInquiryRequest = new UpdateInquiryRequest("updateTitle",
        "updateContent");
    String updateTitle = updateInquiryRequest.getTitle();
    String updateContent = updateInquiryRequest.getContent();

    when(inquiryRepository.findById(anyLong()))
        .thenReturn(Optional.of(inquiry));
    //when
    inquiryServiceImpl.updateInquiry(anyLong(), "username", updateInquiryRequest,UserRoleEnum.MEMBER);

    //then
    assertThat(inquiry.getTitle()).isEqualTo(updateTitle);
    assertThat(inquiry.getContent()).isEqualTo(updateContent);
    verify(inquiryRepository, times(1)).save(any(Inquiry.class));
  }

  @Test
  @DisplayName("inquiry 수정_매니저일때 성공")
  void updateInquiry_RoleManager_success() {
    //given
    Inquiry inquiry = new Inquiry("username", "nickname", "title", "content", false);
    UpdateInquiryRequest updateInquiryRequest = new UpdateInquiryRequest("updateTitle",
        "updateContent");
    String updateTitle = updateInquiryRequest.getTitle();
    String updateContent = updateInquiryRequest.getContent();

    when(inquiryRepository.findById(anyLong()))
        .thenReturn(Optional.of(inquiry));
    //when
    inquiryServiceImpl.updateInquiry(anyLong(), "manager", updateInquiryRequest,UserRoleEnum.MANAGER);

    //then
    assertThat(inquiry.getTitle()).isEqualTo(updateTitle);
    assertThat(inquiry.getContent()).isEqualTo(updateContent);
    verify(inquiryRepository, times(1)).save(any(Inquiry.class));
  }

  @Test
  @DisplayName("inquiry 수정_해당유저의 글이 존재 하지 않을때 예외")
  void updateInquiry_BOARD_NOT_EXIST_throw() {
    //given
    Inquiry inquiry = new Inquiry("username", "nickname", "title", "content", false);
    UpdateInquiryRequest updateInquiryRequest = new UpdateInquiryRequest("updateTitle",
        "updateContent");
    String updateTitle = updateInquiryRequest.getTitle();
    String updateContent = updateInquiryRequest.getContent();
    inquiry.update(updateTitle, updateContent);
    when(inquiryRepository.findById(anyLong()))
        .thenReturn(Optional.of(inquiry));
    //when&then
    assertThatThrownBy(() ->
        inquiryServiceImpl.updateInquiry(anyLong(), "invalid", updateInquiryRequest,UserRoleEnum.MEMBER)).isInstanceOf(
        CustomException.class);
  }

  @Test
  @DisplayName("inquiry 수정_해당 유저가 아닐때 예외")
  void updateInquiry_invalidUser_throw() {
    //given
    Inquiry inquiry = new Inquiry("username", "nickname", "title", "content", false);
    UpdateInquiryRequest updateInquiryRequest = new UpdateInquiryRequest("updateTitle",
        "updateContent");
    String updateTitle = updateInquiryRequest.getTitle();
    String updateContent = updateInquiryRequest.getContent();

    when(inquiryRepository.findById(anyLong()))
        .thenReturn(Optional.of(inquiry));

    //when&then
    assertThatThrownBy(() ->
        inquiryServiceImpl.updateInquiry(anyLong(), "invalid", updateInquiryRequest,UserRoleEnum.MEMBER)).isInstanceOf(
        CustomException.class);
  }

  @Test
  @DisplayName("Inquiry 삭제_해당 유저 일때 성공")
  void deleteInquiry_validUser_success() {
    //given
    User user = new User("member", "username1234", UserRoleEnum.MEMBER, "invalidUser",
        "01022223333", "member@naver.com");
    Inquiry inquiry = new Inquiry(user.getUsername(), user.getNickName(), "title", "content",
        false);

    when(inquiryRepository.findById(anyLong())).thenReturn(Optional.of(inquiry));
    //when
    inquiryServiceImpl.deleteInquiry(anyLong(), "member", UserRoleEnum.MEMBER);
    //then
    verify(inquiryRepository, times(1)).delete(any(Inquiry.class));
  }

  @Test
  @DisplayName("Inquiry 삭제_관리자 일때 성공")
  void deleteInquiry_Role_Manager_success() {
    //given
    User user = new User("manager", "username1234", UserRoleEnum.MANAGER, "invalidUser",
        "01022223333", "member@naver.com");
    Inquiry inquiry = new Inquiry("member", "member", "title", "content",
        false);

    when(inquiryRepository.findById(anyLong())).thenReturn(Optional.of(inquiry));
    //when
    inquiryServiceImpl.deleteInquiry(anyLong(), "manager", UserRoleEnum.MANAGER);
    //then
    verify(inquiryRepository, times(1)).delete(any(Inquiry.class));
  }

  @Test
  @DisplayName("Inquiry 삭제_해당유저가도 아니고 매니저도 아닐때 예외")
  void deleteInquiry_invalidUserAndRoleMEMBER_throw() {

    User user = new User("member", "username1234", UserRoleEnum.MEMBER, "invalidUser",
        "01022223333", "member@naver.com");
    Inquiry inquiry = new Inquiry(user.getUsername(), user.getNickName(), "title", "content",
        false);

    when(inquiryRepository.findById(anyLong())).thenReturn(Optional.of(inquiry));

    //when&then
    assertThatThrownBy(() ->
        inquiryServiceImpl.deleteInquiry(anyLong(), "invalidUser",
            UserRoleEnum.MEMBER)).isInstanceOf(CustomException.class);
  }

  @Test
  @DisplayName("FAQ _대소문자 구분 없이 제목 검색조회(TitleContaining)")
  void searchByKeyword_titleContaining_success() {
    //given
    String keyword = "Title";
    String question = keyword;
    String answer = keyword;

    int page = 1;
    int size = 1;
    Direction direction = Direction.DESC;
    String properties = "createdDate";

   InquiryRequest inquiryRequest = new InquiryRequest("Title", "answer",false);
    Inquiry inquiry = inquiryRequest.toEntity("username","nickName");


    //Page<Inquiry> 객체를 반환하도록 설정
    List<Inquiry> inquiryList = new ArrayList<>();
    inquiryList.add(inquiry);


    PageImpl<Inquiry> pageImpl = new PageImpl<>(inquiryList);

    when(inquiryRepository.findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(question,
        answer, PageRequest.of(page - 1, size, direction, properties))).thenReturn(pageImpl);
    //when
    Result response = inquiryServiceImpl.searchByKeyword(keyword, page, size, direction, properties);
    //then
    assertThat(response.getTotalCount()).isEqualTo(1);

  }

  @Test
  @DisplayName("FAQ _대소문자 구분 없이 내용 검색조회(ContentContaining)")
  void searchByKeyword_contentContaining_success() {
    //given
    String keyword = "Content";
    String question = keyword;
    String answer = keyword;

    int page = 1;
    int size = 1;
    Direction direction = Direction.DESC;
    String properties = "createdDate";

    InquiryRequest inquiryRequest = new InquiryRequest("KEYWORD", "Content",false);
    Inquiry inquiry = inquiryRequest.toEntity("username","nickName");


    //Page<Inquiry> 객체를 반환하도록 설정
    List<Inquiry> inquiryList = new ArrayList<>();
    inquiryList.add(inquiry);


    PageImpl<Inquiry> pageImpl = new PageImpl<>(inquiryList);

    when(inquiryRepository.findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(question,
        answer, PageRequest.of(page - 1, size, direction, properties))).thenReturn(pageImpl);
    //when
    Result response = inquiryServiceImpl.searchByKeyword(keyword, page, size, direction, properties);
    //then
    assertThat(response.getTotalCount()).isEqualTo(1);

  }
}

