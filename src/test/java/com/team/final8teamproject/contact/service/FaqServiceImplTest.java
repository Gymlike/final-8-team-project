package com.team.final8teamproject.contact.service;


import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.DESC;

import com.team.final8teamproject.contact.Repository.FaqRepository;
import com.team.final8teamproject.contact.dto.FaqRequest;
import com.team.final8teamproject.contact.dto.FaqResponse;
import com.team.final8teamproject.contact.dto.UpdateFaqRequest;
import com.team.final8teamproject.contact.entity.Faq;
import com.team.final8teamproject.contact.service.FaqServiceImpl.Result;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * import 시 주의 할점 mockito 패키지가 맞는지 꼭 확인 할것
 */
@ExtendWith(MockitoExtension.class)
class FaqServiceImplTest {

  @Mock
  private FaqRepository faqRepository;

  @InjectMocks
  private FaqServiceImpl faqServiceImpl;


  /**
   * 1.when -> 테스트할 로직 작성 2.given -> 테스트에 필요한 입력값 , 리턴값 등을 작성 3.then -> 검증
   */

  @Test
  @DisplayName("FAQ 등록_성공")
  void saveFaq_success() {
    //given입력값, 리턴값
    FaqRequest faqRequest = mock(FaqRequest.class);
    Faq faq =mock(Faq.class);
    when(faqRequest.toEntity(1L))
        .thenReturn(faq);

    //when
    faqServiceImpl.saveFaq(faqRequest,1L);
    //then
    verify(faqRepository,times(1)).save(faq);
  }
  @Test
  @DisplayName("FAQ 등록_성공")
  void saveFaq_success1() {
    //given
    FaqRequest faqRequest = new FaqRequest("e", "e");
    //Faq faq =mock(Faq.class);
    when(faqRepository.save(any(Faq.class)))
        .thenReturn(faqRequest.toEntity(1L));

    //when
    faqServiceImpl.saveFaq(faqRequest, 1L);
    //then
    verify(faqRepository, times(1)).save(any(Faq.class));
  }

  @Test
  @DisplayName("FAQ 등록_실패 request에 @NotBlank 아닐때")
  void saveFaq_fail() {
    //given

    //Faq faq =mock(Faq.class);
//    when(faqRepository.save(any(Faq.class)))
//        .thenReturn();

    //when
    FaqRequest faqRequest = new FaqRequest("","");
    //then
    //assertThrow; // 실행되지 않는 것을 확인
  }

  /**
   * 페이징처리는 테스는 코드 큰 의미 없다.
   * 스프링부트를 돌려봐야 , 페이지처리 가 잘된지 알 수 있다.
   * assertThat (list 타입 요구함) 과 assertEquals 차이
   */
    @Test
  @DisplayName("FAQ 조회_성공")
  void getFaqList_success() {
        //given

    //when
    Result response = faqServiceImpl.getFaqList(1,10,DESC,"createdDate");
    //then


  }
//  @Test
//  @DisplayName("FAQ 조회_글이 없을때 예외발생")
//  void getFaqList_throw() {
//    //given
//    when(faqRepository.findAll(any(Pageable.class)))
//        .thenReturn(Optional.empty());
//    //when&then
//    Result response = faqServiceImpl.getFaqList(1, 10, DESC, "createdDate");
//    //
//
//
//  }


  @Test
  @DisplayName("FAQ 건당조회_성공")
  void getSelectedFaq_success() {
    //given
    FaqRequest request = new FaqRequest("hello","hello");
    Faq faq = request.toEntity(1L);
    when(faqRepository.findById(anyLong()))
        .thenReturn(Optional.of(faq));

    //when
    FaqResponse response = faqServiceImpl.getSelectedFaq(anyLong());
    //then
    assertEquals(response.getQuestion(),faq.getQuestion());
    assertEquals(response.getAnswer(),faq.getAnswer());  //todo 왜 요구가 리스트지?
    verify(faqRepository).findById(anyLong());
  }

  @Test
  @DisplayName("FAQ 건당조회_id가 없을때 예외")
  void getSelectedFaq_throw() {
    //given
    when(faqRepository.findById(anyLong()))
        .thenReturn(Optional.empty());
    //when&then
    assertThrows(CustomException.class, () -> {
      faqServiceImpl.getSelectedFaq(1L);
    });
  }

  @Test
  @DisplayName("FAQ 검색조회_성공")
  void searchByKeyword() {
  }

  @Test
  @DisplayName("FAQ 수정_성공")
  void updateFaq() {
    //given

    UpdateFaqRequest updateFaqRequest = new UpdateFaqRequest("hello","hello");
    String question = updateFaqRequest.getQuestion();
    String answer = updateFaqRequest.getAnswer();
    Faq faq = new Faq(1L,question,answer);

    faq.update(question,answer);

    when(faqRepository.findById(anyLong()))
        .thenReturn(Optional.of(faq));

    //when
    faqServiceImpl.updateFaq(1L,1L,updateFaqRequest);
    //verify
    verify(faqRepository,times(1)).save(any(Faq.class));
  }


  @Test
  @DisplayName("FAQ 수정_id에 해당 하는 게시글이 존재하지 않을때 예외")
  void updateFaq_throw() {
    //given
    when(faqRepository.findById(1L))
        .thenReturn(Optional.empty());

    //when&then
    assertThrows(CustomException.class, ()-> {
      faqServiceImpl.getSelectedFaq(1L);
    });
  }
//  @Test
//  @DisplayName("FAQ 수정_다른사람이 작성하려고 할때 에러 ")
//  void updateFaq_invalid_user() {
//    //given
//    UpdateFaqRequest updateFaqRequest = new UpdateFaqRequest("hello","hello");
//    String question = updateFaqRequest.getQuestion();
//    String answer = updateFaqRequest.getAnswer();
//
//    User member1 = new User("member1","member1234", UserRoleEnum.MEMBER,"member1","01012345678","member@naver.com",0L);
//    User member2 = new User("member2","member1234", UserRoleEnum.MEMBER,"member2","01022345678","member@naver.com",0L);
//
//    Faq faq = new Faq(member1.getId(),question,answer);
//
//    when(faqRepository.findById(anyLong()))
//        .thenReturn(Optional.of(faq));
//
//    //when&then
//    assertThrows(CustomException.class,()->
//        faqServiceImpl.updateFaq(1L,member2.getId(),updateFaqRequest));
//  }


  @Test
  @DisplayName("FAQ 삭제_성공")
  void deleteFaq() {
  }
}