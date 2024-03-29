package com.team.final8teamproject.contact.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.team.final8teamproject.contact.Repository.NoticeRepository;
import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.NoticeRequest;
import com.team.final8teamproject.contact.dto.NoticeResponse;
import com.team.final8teamproject.contact.dto.UpdateNoticeRequest;
import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.contact.entity.Notice;
import com.team.final8teamproject.contact.service.NoticeServiceImpl.Result;
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

@ExtendWith(MockitoExtension.class)
class NoticeServiceImplTest {

  @Mock
  private NoticeRepository noticeRepository;

  @InjectMocks
  private NoticeServiceImpl noticeServiceImpl;

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
  @DisplayName("공지사항 등록 성공 ")
  void saveNotice_success() {
    //given
    NoticeRequest noticeRequest = NoticeRequest.builder()
        .title("title")
        .content("content")
        .build();
    String imageUrl = "image";
    when(noticeRepository.save(any(Notice.class)))
        .thenReturn(noticeRequest.toEntity(1L, imageUrl));
    //when
    noticeServiceImpl.saveNotice(noticeRequest, 1L, imageUrl);
    //then
    verify(noticeRepository, times(1)).save(any(Notice.class));

  }

  /**
   * CI/CD 때  이 테스트 에러남 인텔리제이에선 한글로 에러메시지를 보내어 테스트 성공이지만
   * CI/CD 빌드할때 테스트의 경우엔 영어로 에러메세지를 보내 테스트 실패로 빌드가 되지 않는다.
   * validation관한 테스트는ci/cd 경우 경우엔 주석처리 요망
   * 근데 굳이 필요할까 생각이 든다.  @NolBlank 처리가 됐으니 위해서 등록 성공했으니 ..!
   */
//  @Test
//  @DisplayName("Notice 등록_실패 request에 @NotBlank 아닐때")
//  void saveNotice_fail() {
//    //given
//    NoticeRequest noticeRequest = NoticeRequest.builder()
//        .title("")
//        .content("content")
//        .build();
//    //when
//    Set<ConstraintViolation<NoticeRequest>> violation = validator.validate(noticeRequest);
//    //then
//    assertThat(violation).isNotEmpty();
//    violation
//        .forEach(error -> {
//          assertThat(error.getMessage()).isEqualTo("This is not blank");
//        });
//  }


  @Test
  @DisplayName("Notice 전체조회_글이 없을때 예외발생")
  void getNoticeList_throw() {
    //given
    int page = 1;
    int size = 10;
    Direction direction = Direction.DESC;
    String properties = "createdDate";

    lenient().when(noticeRepository.findAll(PageRequest.of(page - 1, size, direction, properties)))
        .thenReturn( Page.empty());
    //when&then
    assertThrows(CustomException.class, () -> {
      noticeServiceImpl.getNoticeList(page, size, direction, properties);
    });
  }

  @Test
  @DisplayName("Notice 건당조회_성공")
  void getSelectedNotice_success() {
    //given
    NoticeRequest noticeRequest = NoticeRequest.builder()
        .title("title")
        .content("content")
        .build();
    String title = noticeRequest.getTitle();
    String content = noticeRequest.getContent();
    Notice notice = new Notice(1L, title, content, "imageUrl");
    when(noticeRepository.findById(1L))
        .thenReturn(Optional.of(notice));
    //when
    NoticeResponse noticeResponse = noticeServiceImpl.getSelectedNotice(1L);
    //then
    assertThat(noticeResponse.getTitle()).isEqualTo("title");
    assertThat(noticeResponse.getContent()).isEqualTo("content");
    verify(noticeRepository).findById(anyLong());
  }

  @Test
  @DisplayName("Notice 건당조회_id가 없을때 예외")
  void getSelectedNotice_throw() {
    //given
    when(noticeRepository.findById(anyLong()))
        .thenReturn(Optional.empty());
    //when&then
    assertThatThrownBy(() -> noticeServiceImpl.getSelectedNotice(anyLong())).isInstanceOf(
        CustomException.class);//예외 발생하는 경우
  }


  @Test
  @DisplayName("Notice 수정_성공")
  void updateNotice_success() {
    //given
    Notice notice = new Notice(1L, "title", "content", "imageUrl");
    UpdateNoticeRequest updateNoticeRequest = new UpdateNoticeRequest("updateT", "updateC");

    when(noticeRepository.findById(1L))
        .thenReturn(Optional.of(notice));
    //when
    noticeServiceImpl.updateNotice(1L, 1L, updateNoticeRequest, "inageUrl");
    //then
    assertThat(notice.getTitle()).isEqualTo("updateT");
    assertThat(notice.getContent()).isEqualTo("updateC");
    verify(noticeRepository).findById(1L);
  }

  @Test
  @DisplayName("Notice 수정_id에 해당 하는 게시글이 존재하지 않을때 예외")
  void updateFaq_throw() {
    //given
    // UpdateNoticeRequest updateNoticeRequest =new UpdateNoticeRequest("title","content")
    when(noticeRepository.findById(1L))
        .thenReturn(Optional.empty());

    //when&then
    assertThatThrownBy(
        () -> noticeServiceImpl.updateNotice(1L, 1L, new UpdateNoticeRequest("title", "content"),
            "imageUrl")).isInstanceOf(CustomException.class);//예외 발생하는 경우
  }


  @Test
  @DisplayName("Notice 삭제_성공")
  void deleteNotice() {
    //given
    Notice notice = new Notice(1L, "title", "content", "imageUrl");
    when(noticeRepository.findById(1L))
        .thenReturn(Optional.of(notice));
    //when
    noticeServiceImpl.deleteNotice(1L, 1L);
    //then
    verify(noticeRepository).delete(notice);
  }


  @Test
  @DisplayName("Notice 삭제_해당 게시글이 존재하지 않을때_예외")
  void deleteNotice_throw() {
    //given
    when(noticeRepository.findById(anyLong()))
        .thenReturn(Optional.empty());
    //when&then
    assertThatThrownBy(
        () -> noticeServiceImpl.deleteNotice(anyLong(), 1L))
        .isInstanceOf((CustomException.class));
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

    NoticeRequest noticeRequest = new NoticeRequest("Title", "answer");
    Notice notice = noticeRequest.toEntity(1L,"이미지");


    //Page<Notice> 객체를 반환하도록 설정
    List<Notice> noticeList = new ArrayList<>();
    noticeList.add(notice);


    PageImpl<Notice> pageImpl = new PageImpl<>(noticeList);

    when(noticeRepository.findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(question,
        answer, PageRequest.of(page - 1, size, direction, properties))).thenReturn(pageImpl);
    //when
    Result response = noticeServiceImpl.searchByKeyword(keyword, page, size, direction, properties);
    //then
    assertThat(response.getTotalCount()).isEqualTo(1);

  }

  @Test
  @DisplayName("FAQ _대소문자 구분 없이 내용 검색조회(ContentContaining)")
  void searchByKeyword_ContentContaining_success() {
    //given
    String keyword = "Content";
    String question = keyword;
    String answer = keyword;

    int page = 1;
    int size = 1;
    Direction direction = Direction.DESC;
    String properties = "createdDate";

    NoticeRequest noticeRequest = new NoticeRequest("Title", "Content");
    Notice notice = noticeRequest.toEntity(1L,"이미지");


    //Page<Notice> 객체를 반환하도록 설정
    List<Notice> noticeList = new ArrayList<>();
    noticeList.add(notice);


    PageImpl<Notice> pageImpl = new PageImpl<>(noticeList);

    when(noticeRepository.findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(question,
        answer, PageRequest.of(page - 1, size, direction, properties))).thenReturn(pageImpl);
    //when
    Result response = noticeServiceImpl.searchByKeyword(keyword, page, size, direction, properties);
    //then
    assertThat(response.getTotalCount()).isEqualTo(1);

  }


}