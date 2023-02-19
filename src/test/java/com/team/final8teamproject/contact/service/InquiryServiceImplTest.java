package com.team.final8teamproject.contact.service;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static reactor.core.publisher.Mono.when;

import com.team.final8teamproject.contact.Comment.repository.ContactCommentRepository;
import com.team.final8teamproject.contact.Comment.service.ContactCommentServiceImpl;
import com.team.final8teamproject.contact.Repository.InquiryRepository;
import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.UpdateInquiryRequest;
import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InquiryServiceImplTest {

  @Mock
  private InquiryRepository inquiryRepository;
  @InjectMocks
  private InquiryServiceImpl inquiryService;

  @Mock
  private ContactCommentRepository contactCommentRepository;

  @InjectMocks
  private ContactCommentServiceImpl contactCommentService;

//  @Spy
//  private JwtUtil jwtUtil;

//  @BeforeEach
//  void prepare() {
//    ReflectionTestUtils.setField(jwtUtil,
//        "secretKey", // jwtUtil의 secretKey 값이 저장될 변수
//        "7ZWt7ZW0OTntmZTsnbTtjIXtlZzqta3snYTrhIjrqLjshLjqs4TroZzrgpjslYTqsIDsnpDtm4zrpa3tlZzqsJzrsJzsnpDrpbzrp4zrk6TslrTqsIDsnpA=");//secretKey 값
//    jwtUtil.init();//jwtUtil 에서 @PostConstructor 가 동작하지 않기 떄문에 , 임의로 실행시켜야함.
//  }

  @Test
  @DisplayName("문의글 작성 -성공")
  void createInquiry() {
    //given
    User user = new User("pororo", "pororo1234", "01012345678", "pororo@naver.com", "뽀로로",
        UserRoleEnum.MEMBER);
    String username = user.getUsername();

    String title = "제목";
    String content = "내용";
    Boolean secretCheckBox = false;

    InquiryRequest inquiryRequest = InquiryRequest.builder()
        .title(title)
        .content(content)
        .secretCheckBox(secretCheckBox)
        .build();
    //save 리턴값도 x , 행위지정 도 없기 때문에 실행한 척 만함
//    when((Publisher<?>) inquiryRepository.save(any(Inquiry.class)))
//        .thenReturn(inquiryRequest.toEntity(username));
    //when
    inquiryService.createInquiry(inquiryRequest, username, user.getNickName());
    //then
    verify(inquiryRepository, times(1)).save(any(Inquiry.class));
  }

  @Test
  @DisplayName("문의글 업데이트 ")
  void updateInquiry() {
    //given
    String title = "title";
    String content = "content";
    UpdateInquiryRequest updateInquiryRequest = new UpdateInquiryRequest("pororo","porororororo");
    Inquiry inquiry = updateInquiryRequest.toEntity("pororo");
    String username = inquiry.getUsername();
    Mockito.when(inquiryRepository.findById(anyLong()))
        .thenReturn(Optional.of(inquiry));

    //when
    inquiryService.updateInquiry(anyLong(), username, inquiry.getNickName(), updateInquiryRequest);
    //then
    verify(inquiryRepository,times(1)).save(inquiry);
  }

  @Test
  void getInquiry() {
  }

//  @Test
//  @DisplayName("해당 문의글 조회 -성공")
//  void getSelectedInquiry() {
//    //given 입력값, 목킹 리턴값
//    User user = new User("pororo12","pororo12345 ","010-2345-6789","pororo@naver.com","뽀로로",UserRoleEnum.MEMBER);
//    Inquiry inquiry = new Inquiry("pororo12","문의","글임",false);
////pa
//    Mockito.when(inquiryRepository.findById(anyLong()))
//        .thenReturn(Optional.of(inquiry));
//    //comment create
//    ContactComment comment = new ContactComment("comment",anyLong(),"pororo12",null);
//  //  List<ContactComment> parentComments = contactCommentRepository. findAllByInquiryIdAndParentIsNull(id);
////    Mockito.when(contactCommentRepository.findAllByInquiryIdAndParentIsNull(anyLong()))
////        .thenReturn(Optional.of(comment));
//    //when
//    InquiryResponse inquiryResponse = inquiryService.getSelectedInquiry(anyLong());
//    //then
//    assertThat(inquiryResponse.getTitle()).isEqualTo(inquiry.getTitle());
//    assertThat(inquiryResponse.getContent()).isEqualTo(inquiry.getContent());
//
//    verify(inquiryRepository,times(1)).findById(anyLong());
//  }

  @Test
  void searchByKeyword() {
  }

  @Test
  void deleteInquiry() {
  }

  @Test
  void deleteManager() {
  }
}