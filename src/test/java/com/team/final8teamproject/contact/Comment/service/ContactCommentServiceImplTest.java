package com.team.final8teamproject.contact.Comment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.team.final8teamproject.contact.Comment.dto.CreateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.dto.UpdateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import com.team.final8teamproject.contact.Comment.repository.ContactCommentRepository;
import com.team.final8teamproject.contact.Repository.InquiryRepository;
import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.UpdateInquiryRequest;
import com.team.final8teamproject.contact.entity.Faq;
import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContactCommentServiceImplTest {

  @Mock
  ContactCommentRepository contactCommentRepository;
  @Mock
  InquiryRepository inquiryRepository;

  @InjectMocks
  ContactCommentServiceImpl contactCommentService;

//--------------Validation 위한 설정 ----------------------

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
// -------------------------------------------------------

  @Test
  @DisplayName("부모댓글이 없는 경우 - 댓글 등록 성공 ")
  void saveInquiryComment_comment_success() {
    //given
    int depth = 1;
    CreateContactCommentRequest createContactRequest = new CreateContactCommentRequest("comments",
        null);
    Inquiry inquiry = new Inquiry("username","nick","title","content",false);

    when(contactCommentRepository.save(any(ContactComment.class)))
        .thenReturn(createContactRequest.toEntity(1L, "username", "nickname", null, depth));
    when(inquiryRepository.existsById(1L))
        .thenReturn(true);

    //when
    contactCommentService.saveInquiryComment(1L, createContactRequest, "username", "nickname");
    //then
    verify(contactCommentRepository, times(1)).save(any(ContactComment.class));
    assertThat(createContactRequest.toEntity(1L, "username", "nickname", //저장된 댓글의 depth가 1이면 댓글임
        null, depth).getDepth()).isEqualTo(1);

  }

  @Test
  @DisplayName("부모댓글이 없는 경우/inquiryId 없을 시 - 댓글등록실패,예외처리 ")
  void saveInquiryComment_comment_throw() {
    //given
    int depth = 1;
    CreateContactCommentRequest createContactRequest =
        new CreateContactCommentRequest("comments", null);

    when(inquiryRepository.existsById(1L))
        .thenReturn(false);
    //when&then
    assertThatThrownBy(() -> contactCommentService.saveInquiryComment(1L, createContactRequest,
        "username", "nickname")).isInstanceOf(CustomException.class);
  }

  //------------------------------------------------------------------------------
//----------------------부모댓글이 있는 경우 ( 대댓글 저장 )-----------------------------
  @Test
  @DisplayName("부모댓글이 있는 경우 - 대댓글 등록 성공 ")
  void saveInquiryComment_reComment() {
    //given
    //부모댓글 작성
    Inquiry inquiry = new Inquiry("username","nick","title","content",false);

    ContactComment parent = new ContactComment("부모댓글", "username", 1L,
        "nickName", null, 1);

    CreateContactCommentRequest createContactRequest =
        new CreateContactCommentRequest("comments", parent.getId());
    // 부모댓글과 자식댓글이 될 inquiryId 같은지 체크 true 일때
    parent.isInquiryId(1L);
    int depth = parent.getDepth() + 1;
    when(contactCommentRepository.save(any(ContactComment.class)))
        .thenReturn(createContactRequest.toEntity(1L, "username", "nickname", parent, depth));
    when(inquiryRepository.existsById(1L))
        .thenReturn(true);

    //when
    contactCommentService.saveInquiryComment(1L, createContactRequest, "username", "nickname");
    //then
    verify(contactCommentRepository, times(1)).save(any(ContactComment.class));
    assertThat(createContactRequest.toEntity(1L, "username", "nickname", parent,
            depth)//저장된 댓글의 depth가 2이면 대댓글임
        .getDepth()).isEqualTo(2);
  }


  //-------------부모댓글 있고 & 자식댓글(대댓글)인 경우 : ( 대대댓글 저장 )----------------

  @Test
  public void saveInquiryComment_WithParentComment_ShouldSaveChildComment() {
    // given
    Long inquiryId = 1L;
    Long parentId = 2L;
    String username = "test";
    String nickName = "TestUser";
    Inquiry inquiry = new Inquiry("username","nick","title","content",false);

    CreateContactCommentRequest request = new CreateContactCommentRequest("test comment", parentId);

    ContactComment parent = request.toEntity(1L,"username","nickName",null,1);

    when(inquiryRepository.existsById(inquiryId)).thenReturn(true);
    when(contactCommentRepository.findById(parentId)).thenReturn(Optional.of(parent));

    // when
    contactCommentService.saveInquiryComment(inquiryId, request, username, nickName);

    // then
    verify(contactCommentRepository).findById(parentId);
    verify(contactCommentRepository).save(any(ContactComment.class));
  }


//-----------------------------------------------------------------------------

  @Test
  @DisplayName("부모댓글이 없는 경우 예외처리  - 대댓글,대댓글 등록 실패")
  void saveInquiryComment_reComment_throw() {
    //given
    CreateContactCommentRequest createContactRequest = new CreateContactCommentRequest("comments",
        null);

    //when&then
    assertThatThrownBy(() -> contactCommentService.saveInquiryComment(1L, createContactRequest,
        "username", "nickname")).isInstanceOf(CustomException.class);
  }

  //---------------------------------------------------------------------------
  @Test
  @DisplayName("부모댓글이 있지만 해당 부모댓글이 없는 경우 예외처리  - 대댓글,대댓글 등록 실패")
  void saveInquiryComment_reComment_throw2() {
    //given
    Inquiry inquiry = new Inquiry("username","nick","title","content",false);

    CreateContactCommentRequest createContactCommentRequest = new CreateContactCommentRequest(
        "부모댓글",
        1L);
    ContactComment parent = createContactCommentRequest.toEntity(1L, "username",
        "nickName", null, 1);
    CreateContactCommentRequest createContactCommentRequest1 = new CreateContactCommentRequest(
        "대댓글",
        1L);
    ContactComment child = createContactCommentRequest1.toEntity(1L, "username1",
        "nickName1", parent, 2);

    lenient().when(contactCommentRepository.findById(createContactCommentRequest1.getParentId()))
        .thenReturn(Optional.empty());

    //when&then
    assertThatThrownBy(
        () -> contactCommentService.saveInquiryComment(1L, createContactCommentRequest1,
            "username1", "nickName1")).isInstanceOf(CustomException.class);
  }

//-----------------------------------------------------------------------------

  @Test
  @DisplayName("댓글 수정 성공_해당유저일때 ")
  void updateInquiryComment_valid_success() {

    //given
    Inquiry inquiry = new Inquiry("username","nick","title","content",false);

    ContactComment comment = new ContactComment("댓글", "username", 1L, "닉네임", null, 1);
    UpdateContactCommentRequest updateCommentRequest = new UpdateContactCommentRequest("수정글");
    String updateComment = updateCommentRequest.getComments();

    when(contactCommentRepository.findById(1L))
        .thenReturn(Optional.of(comment));
    //when
    contactCommentService.updateInquiryComment(1L, updateCommentRequest, "username",
        UserRoleEnum.MEMBER);
    //then
    assertThat(comment.getComments()).isEqualTo(updateComment);
    verify(contactCommentRepository, times(1)).save(any(ContactComment.class));


  }

  @Test
  @DisplayName("댓글 수정 성공_매니저 일때 ")
  void updateInquiryComment_roleManager_success() {

    //given
    ContactComment comment = new ContactComment("댓글", "username", 1L, "닉네임", null, 1);
    UpdateContactCommentRequest updateCommentRequest = new UpdateContactCommentRequest("수정글");
    String updateComment = updateCommentRequest.getComments();

    when(contactCommentRepository.findById(1L))
        .thenReturn(Optional.of(comment));
    //when
    contactCommentService.updateInquiryComment(1L, updateCommentRequest, "invalidUsername",
        UserRoleEnum.MANAGER);
    //then
    assertThat(comment.getComments()).isEqualTo(updateComment);
    verify(contactCommentRepository, times(1)).save(any(ContactComment.class));


  }

  @Test
  @DisplayName("댓글 수정 예외- 유효한 댓글이 아닐때  ")
  void updateInquiryComment_invalidComment_throw() {
    //given
    ContactComment comment = new ContactComment("댓글", "username",1L, "닉네임", null, 1);
    UpdateContactCommentRequest updateCommentRequest = new UpdateContactCommentRequest("수정글");
    when(contactCommentRepository.findById(1L))
        .thenReturn(Optional.empty());

    //when&then
    assertThatThrownBy(
        () -> contactCommentService.updateInquiryComment(1L, updateCommentRequest, "username",
            UserRoleEnum.MEMBER))
        .isInstanceOf(CustomException.class);
  }

  @Test
  @DisplayName("댓글 수정 예외- 유효한 username 또는 매니저가 아닐때  ")
  void updateInquiryComment_invalidUsername_noManager_throw() {
    //given
    ContactComment comment = new ContactComment("댓글", "username",1L, "닉네임", null, 1);
    UpdateContactCommentRequest updateCommentRequest = new UpdateContactCommentRequest("수정글");
    when(contactCommentRepository.findById(1L))
        .thenReturn(Optional.of(comment));

    //when&then
    assertThatThrownBy(() -> contactCommentService.updateInquiryComment(1L, updateCommentRequest,
        "invalidUsername", UserRoleEnum.MEMBER))
        .isInstanceOf(CustomException.class);
  }

  //-----------------------------------------------------------------------------
  @Test
  @DisplayName("댓글 삭제 성공_해당 유저글일때 ")
  void deleteInquiryComment_valid_success() {
    //given
    ContactComment comment = new ContactComment("댓글", "username", 1L, "닉네임", null, 1);
    when(contactCommentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));

    //when
    contactCommentService.deleteInquiryComment(comment.getId(), "username", UserRoleEnum.MEMBER);
    //then
    verify(contactCommentRepository, times(1)).deleteById(comment.getId());
  }

  @Test
  @DisplayName("댓글 삭제 성공_해당 유저가 아닌 관리자 일때 ")
  void deleteInquiryComment_UserRole_manager_success() {
    //given
    ContactComment comment = new ContactComment("댓글", "username",1L, "닉네임", null, 1);
    when(contactCommentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));
    //when
    contactCommentService.deleteInquiryComment(comment.getId(), "manager", UserRoleEnum.MANAGER);
    //then
    verify(contactCommentRepository, times(1)).deleteById(comment.getId());
  }

  @Test
  @DisplayName("댓글 삭제 예외- 유효하지 않은 댓글 시  ")
  void deleteInquiryComment_invalidComment_throw() {
    //given
    ContactComment comment = new ContactComment("댓글", "username", 1L, "닉네임", null, 1);
    when(contactCommentRepository.findById(comment.getId())).thenReturn(Optional.empty());
    //when&then
    assertThatThrownBy(
        () -> contactCommentService.deleteInquiryComment(comment.getId(), "manager",
            UserRoleEnum.MANAGER))
        .isInstanceOf(CustomException.class);
  }

  @Test
  @DisplayName("댓글 삭제 예외 ")
  void deleteInquiryComment_invalidUserAndNoManager_throw() {
    //given
    ContactComment comment = new ContactComment("댓글", "username",1L, "닉네임", null, 1);
    when(contactCommentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));
    //when&then
    assertThatThrownBy(
        () -> contactCommentService.deleteInquiryComment(comment.getId(), "invalidUser",
            UserRoleEnum.MEMBER))
        .isInstanceOf(CustomException.class);
  }


  @Test
  @DisplayName("문의글에 해당하는 댓글 리스트 같이 반환하는 서비스 호출_성공")
  void findAllByInquiryIdAndParentIsNull_success() {
    //given
    ContactComment comment1 = new ContactComment("comment1", "usernames", 1L, "nickName", null, 1);
    ContactComment comment2 = new ContactComment("comment2", "usernames",1L, "nickName", comment1,
        2);

    List<ContactComment> contactCommentList = new ArrayList<>();
    contactCommentList.add(comment1);
    contactCommentList.add(comment2);

    when(contactCommentRepository.findAllByInquiryIdAndParentIsNull(1L))
        .thenReturn(contactCommentList);
    //when
    List<ContactComment> response = contactCommentService.findAllByInquiryIdAndParentIsNull(1L);
    //then
    assertThat(response.size()).isEqualTo(2);
    assertThat(response.get(0).getComments()).isEqualTo("comment1");
    assertThat(response.get(1).getComments()).isEqualTo("comment2");

  }

  @Test
  @DisplayName("contactCommentRepository.findAllByInquiryIdAndParentIsNull(1L) 발생하는 런타임 에러를 잘 처리하는지 확인")
  void findAllByInquiryIdAndParentIsNull_fail() {
    //given
    when(contactCommentRepository.findAllByInquiryIdAndParentIsNull(1L))
        .thenThrow(new RuntimeException("Error occurred while retrieving comment list."));
    //when
    Throwable exception = assertThrows(RuntimeException.class, () ->
        contactCommentService.findAllByInquiryIdAndParentIsNull(1L)
    );
    //then
    assertThat(exception.getMessage())
        .isEqualTo("Error occurred while retrieving comment list.");
  }


  @Test
  @DisplayName("해당 문의글의 댓글 삭제하는 서비스 _ 성공")
  void deleteAllByInquiryId_success() {
    //given
    Long inquiryId = 1L;
    //when
    contactCommentService.deleteAllByInquiryId(1L);
    //then
    verify(contactCommentRepository,times(1)).deleteAllByInquiryId(inquiryId);
  }

}