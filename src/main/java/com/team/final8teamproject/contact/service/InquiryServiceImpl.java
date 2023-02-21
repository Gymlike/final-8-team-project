package com.team.final8teamproject.contact.service;

import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import com.team.final8teamproject.contact.Comment.service.ContactCommentServiceImpl;
import com.team.final8teamproject.contact.Repository.InquiryRepository;
import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.InquiryResponse;
import com.team.final8teamproject.contact.dto.UpdateInquiryRequest;
import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import com.team.final8teamproject.user.service.UserService;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InquiryServiceImpl implements InquiryService {

  private final InquiryRepository inquiryRepository;
  private final ContactCommentServiceImpl contactCommentService;


  @Transactional
  @Override
  public void createInquiry(InquiryRequest inquiryRequest, String username, String nickName) {
    Inquiry inquiry = inquiryRequest.toEntity(username,nickName);
    inquiryRepository.save(inquiry);
  }


  @Transactional
  @Override
  public void updateInquiry(Long id, String username, UpdateInquiryRequest updateInquiryRequest) {
    String title = updateInquiryRequest.getTitle();
    String content = updateInquiryRequest.getContent();

    Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(
        () -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST)
    );
    if(inquiry.isWriter(username)){
      inquiry.update(title, content);
      inquiryRepository.save(inquiry);
    } else {
      throw new CustomException(ExceptionStatus.WRONG_USER_T0_CONTACT);
    }
  }


  @Transactional(readOnly = true)
  @Override
  public Result getInquiry(int page, int size, Direction direction,
      String properties) {
    Page<Inquiry> inquiryListPage = inquiryRepository.findAll(
        PageRequest.of(page - 1, size, direction, properties));
    int totalCount = (int) inquiryListPage.getTotalElements();
    if(inquiryListPage.isEmpty()){
      throw new CustomException(ExceptionStatus.POST_IS_EMPTY);
    }
    List<InquiryResponse> inquiryResponses = inquiryListPage.stream().map(InquiryResponse::new)
        .toList();
    int countList = size;
    int countPage = 5;//todo 리팩토링때  10으로 변경예정
    int totalPage = totalCount / countList;
    if (totalCount % countList > 0) {
      totalPage++;
    }
    if (totalPage < page) {
      page = totalPage;
    }
    return new Result(page, totalCount, countPage, totalPage, inquiryResponses);
  }


  /**
   * 건당 문의 글 조회 시
   *
   * @param id 문의글 아이디
   * @return 문의글 , 글에 해당하는 댓글, 대댓글
   */
  @Transactional(readOnly = true)
  @Override
  public InquiryResponse getSelectedInquiry(Long id) {
    Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(
        () -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));
    List<ContactComment> parentComments = contactCommentService.findAllByInquiryIdAndParentIsNull(
        id);
    return new InquiryResponse(inquiry, parentComments);
  }

  @Transactional(readOnly = true)
  @Override
  public Result searchByKeyword(String keyword, int page, int size,
      Direction direction, String properties) {
      String title = keyword;
      String content = keyword;

    Page<Inquiry> inquiryListPage = inquiryRepository.findAllByTitleContainingOrContentContaining(
        title, content, PageRequest.of(page - 1, size, direction, properties));
    int totalCount = (int) inquiryListPage.getTotalElements();
    if(inquiryListPage.isEmpty()){
      throw new CustomException(ExceptionStatus.POST_IS_EMPTY);
    }
    List<InquiryResponse> inquiryResponses = inquiryListPage.stream().map(InquiryResponse::new)
        .toList();
    int countList = size;
    int countPage = 5;//todo 리팩토링때  10으로 변경예정
    int totalPage = totalCount / countList;
    if (totalCount % countList > 0) {
      totalPage++;
    }
    if (totalPage < page) {
      page = totalPage;
    }
    return new Result(page, totalCount, countPage, totalPage, inquiryResponses);
  }

  @Transactional
  @Override
  public void deleteInquiry(Long id, String username) {
    Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(
        () -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST)
    );
    if (inquiry.isWriter(username)) {
      inquiryRepository.delete(inquiry);
      // 문의글 해당 댓글 삭제
      contactCommentService.deleteAllByInquiryId(id);
    } else {
      throw new CustomException(ExceptionStatus.WRONG_USER_T0_CONTACT);
    }
  }


  /**
   * 관리자가 유저 글 삭제 기능
   */
  @Transactional
  @Override
  public void deleteManager(Long id) {
    Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(
        () -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST)
    );
    inquiryRepository.delete(inquiry);
  }

  @Override
  public Inquiry findById(Long inquiryId) {
    Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(
        () -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST)
    );
    return inquiry;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class Result<T> {

    private int page;
    private int totalCount;
    private int countPage;
    private int totalPage;
    private T data;

    public Result(int totalCount, T data) {
      this.totalCount = totalCount;
      this.data = data;
    }

    public Result(int page, int totalCount, int countPage, int totalPage, T data) {
      this.page = page;
      this.totalCount = totalCount;
      this.countPage = countPage;
      this.totalPage = totalPage;
      this.data = data;
    }
  }
}


