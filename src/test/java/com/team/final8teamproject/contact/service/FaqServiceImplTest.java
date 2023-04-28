package com.team.final8teamproject.contact.service;

//AssertJ

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.team.final8teamproject.contact.Repository.FaqRepository;
import com.team.final8teamproject.contact.dto.FaqRequest;
import com.team.final8teamproject.contact.dto.FaqResponse;
import com.team.final8teamproject.contact.dto.UpdateFaqRequest;
import com.team.final8teamproject.contact.entity.Faq;
import com.team.final8teamproject.contact.service.FaqServiceImpl.Result;
import com.team.final8teamproject.share.exception.CustomException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;


/**
 * import 시 주의 할점 mockito 패키지가 맞는지 꼭 확인 할것
 */
@ExtendWith(MockitoExtension.class)
class FaqServiceImplTest {

  @Mock
  private FaqRepository faqRepository;

  @InjectMocks
  private FaqServiceImpl faqServiceImpl;
  //-------------validation 설정 ------------------------
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
  //----------------------------------------------------
  /**
   * 1.when -> 테스트할 로직 작성 2.given -> 테스트에 필요한 입력값 , 리턴값 등을 작성 3.then -> 검증
   * 페이징처리는 테스는 코드 큰 의미 없다.
   * 스프링부트를 돌려봐야 , 페이지처리 가 잘된지 알 수 있다.
   * AssertJ 의 assertThat
   * AssertJ 의 예외 테스트
   */

  /**
   * 튜텨님 방법
   */
  @Test
  @DisplayName("FAQ 등록_성공")
  void saveFaq_success() {
    //given 입력값, 리턴값
    FaqRequest faqRequest = mock(FaqRequest.class);
    Faq faq = mock(Faq.class);
    when(faqRequest.toEntity(1L))
        .thenReturn(faq);

    //when
    faqServiceImpl.saveFaq(faqRequest, 1L);
    //then
    verify(faqRepository, times(1)).save(faq);
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

  /**
   * CI/CD 때  이 테스트 에러남 인텔리제이에선 한글로 에러메시지를 보내어 테스트 성공이지만 CI/CD 빌드할때 테스트의 경우엔 영어로 에러메세지를 보내 테스트 실패로
   * 빌드가 되지 않는다. validation관한 테스트는ci/cd 경우 경우엔 주석처리 요망 근데 굳이 이 테스트가 필요할까 생각이 든다 -> 문제가 됐다면 다른 테스트에서
   * 성공이 나오지 못하니
   */
//  @Test
//  @DisplayName("FAQ 등록_실패 request에 @NotBlank 아닐때")
//  void saveFaq_valid_fail() {
//
//    //given
//    FaqRequest faqRequest = new FaqRequest("", "1111");
//    //when
//    Set<ConstraintViolation<FaqRequest>> violation = validator.validate(faqRequest);
//    //then
//    assertThat(violation).isNotEmpty();
//    violation
//        .forEach(error -> {
//          assertThat(error.getMessage()).isEqualTo("This is not blank");
//        });
//  }
  @Test
  @DisplayName("FAQ 전체조회_글이 없을때 예외발생")
  void getFaqList_throw() {
    //given
    int page = 1;
    int size = 10;
    Direction direction = Direction.DESC;
    String properties = "createdDate";

    lenient().when(faqRepository.findAll(PageRequest.of(page - 1, size, direction, properties)))
        .thenReturn(Page.empty());
    //when&then
    assertThrows(CustomException.class, () -> {
      faqServiceImpl.getFaqList(page, size, direction, properties);
    });
  }


  @Test
  @DisplayName("FAQ 건당조회_성공")
  void getSelectedFaq_success() {
    //given
    FaqRequest request = new FaqRequest("hello", "hello");
    Faq faq = request.toEntity(1L);
    when(faqRepository.findById(anyLong()))
        .thenReturn(Optional.of(faq));

    //when
    FaqResponse response = faqServiceImpl.getSelectedFaq(anyLong());
    //then
    assertThat(response.getQuestion()).isEqualTo(faq.getQuestion());
    assertThat(response.getAnswer()).isEqualTo((faq.getAnswer()));
    verify(faqRepository).findById(anyLong());
  }

  @Test
  @DisplayName("FAQ 건당조회_해당 유저의 글이 없을때 예외")
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
  @DisplayName("FAQ 수정_성공")
  void updateFaq_success() {
    //given
    Faq faq = new Faq(1L, "question", "answer");
    UpdateFaqRequest updateFaqRequest = new UpdateFaqRequest("updateQ", "updateA");
    String question = updateFaqRequest.getQuestion();
    String answer = updateFaqRequest.getAnswer();

    faq.update(question, answer);

    when(faqRepository.findById(anyLong()))
        .thenReturn(Optional.of(faq));

    //when
    faqServiceImpl.updateFaq(1L, 1L, updateFaqRequest);
    //then&verify
    assertThat(faq.getQuestion()).isEqualTo("updateQ");
    assertThat(faq.getAnswer()).isEqualTo("updateA");
    verify(faqRepository, times(1)).save(any(Faq.class));
  }


  @Test
  @DisplayName("FAQ 수정_id에 해당 하는 게시글이 존재하지 않을때 예외")
  void updateFaq_throw() {
    //given
    when(faqRepository.findById(1L))
        .thenReturn(Optional.empty());

    //when&then
    assertThrows(CustomException.class, () -> {
      faqServiceImpl.updateFaq(1L, 1L, new UpdateFaqRequest("question", "answer"));
    });
  }

  @Test
  @DisplayName("FAQ 수정_다른사람이 작성하려고 할때 에러 ")
  void updateFaq_invalid_user() {
    //given
    UpdateFaqRequest updateFaqRequest = new UpdateFaqRequest("hello", "hello");
    String question = updateFaqRequest.getQuestion();
    String answer = updateFaqRequest.getAnswer();

    Faq faq = new Faq(1L, question, answer);

    when(faqRepository.findById(1L))
        .thenReturn(Optional.of(faq));

    //when&then
    assertThatThrownBy(() ->
        faqServiceImpl.updateFaq(1L, 2L, updateFaqRequest)).isInstanceOf(CustomException.class);

  }


  @Test
  @DisplayName("FAQ 삭제_성공")
  void deleteFaq() {
    //given
    Faq faq = new Faq(1L, "ee", "ee");
    when(faqRepository.findById(faq.getId())).thenReturn(Optional.of(faq));
    //when
    faqServiceImpl.deleteFaq(faq.getId(), 1L);
    //then
    verify(faqRepository, times(1)).delete(any(Faq.class));
  }

  @Test
  @DisplayName("FAQ 삭제_해당 게시글이 존재하지 않을때_예외")
  void deleteFaq_throw() {
    //given
    when(faqRepository.findById(1L))
        .thenReturn(Optional.empty());
    //when&then
    assertThrows(CustomException.class, () -> {
      faqServiceImpl.deleteFaq(1L, anyLong());
    });
  }


  @Test
  @DisplayName("FAQ _대소문자 구분 없이 질문 검색조회(questionContaining)")
  void searchByKeyword_questionContaining_success() {
    //given
    String keyword = "keyword";
    String question = keyword;
    String answer = keyword;

    int page = 1;
    int size = 1;
    Direction direction = Direction.DESC;
    String properties = "createdDate";


    FaqRequest faqRequest = new FaqRequest("KEYWORD", "키워드");
    Faq faq = faqRequest.toEntity(1L);
    FaqRequest faqRequest1 = new FaqRequest("keyword", "키워드");
    Faq faq1 = faqRequest1.toEntity(1L);

    //Page<Faq> 객체를 반환하도록 설정
    List<Faq> faqList = new ArrayList<>();
    faqList.add(faq);
    faqList.add(faq1);
    PageImpl<Faq> pageImpl = new PageImpl<>(faqList);

    when(faqRepository.findAllByQuestionContainingIgnoreCaseOrAnswerContainingIgnoreCase(question,
        answer, PageRequest.of(page - 1, size, direction, properties))).thenReturn(pageImpl);
    //when
    Result response = faqServiceImpl.searchByKeyword(keyword, page, size, direction, properties);
    //then

    assertThat(response.getTotalCount()).isEqualTo(2);


  }

  @Test
  @DisplayName("FAQ _대소문자 구분 없이 답변 검색조회(answerContaining)")
  void searchByKeyword_answerContaining_success() {
    //given
    String keyword = "answer";
    String question = keyword;
    String answer = keyword;

    int page = 1;
    int size = 1;
    Direction direction = Direction.DESC;
    String properties = "createdDate";

    FaqRequest faqRequest = new FaqRequest("KEYWORD", "answer");
    Faq faq = faqRequest.toEntity(1L);
    FaqRequest faqRequest1 = new FaqRequest("keyword", "Answer");
    Faq faq1 = faqRequest1.toEntity(1L);


    //Page<Faq> 객체를 반환하도록 설정
    List<Faq> faqList = new ArrayList<>();
    faqList.add(faq);
    faqList.add(faq1);


    PageImpl<Faq> pageImpl = new PageImpl<>(faqList);

    when(faqRepository.findAllByQuestionContainingIgnoreCaseOrAnswerContainingIgnoreCase(question,
        answer, PageRequest.of(page - 1, size, direction, properties))).thenReturn(pageImpl);
    //when
    Result response = faqServiceImpl.searchByKeyword(keyword, page, size, direction, properties);
    //then
    assertThat(response.getTotalCount()).isEqualTo(2);

  }

}
