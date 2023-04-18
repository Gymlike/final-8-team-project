package com.team.final8teamproject.contact.service;

import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import com.team.final8teamproject.contact.Comment.service.ContactCommentServiceImpl;
import com.team.final8teamproject.contact.Repository.InquiryRepository;
import com.team.final8teamproject.contact.dto.FaqResponse;
import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.InquiryResponse;
import com.team.final8teamproject.contact.dto.UpdateInquiryRequest;
import com.team.final8teamproject.contact.entity.Faq;
import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.contact.service.FaqServiceImpl.Result;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import jakarta.validation.Valid;
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


  @Override
  public void createInquiry(@Valid InquiryRequest inquiryRequest, String username,
      String nickName) {
    Inquiry inquiry = inquiryRequest.toEntity(username, nickName);
    inquiryRepository.save(inquiry);
  }


  @Transactional
  @Override
  public void updateInquiry(Long id, String username, UpdateInquiryRequest updateInquiryRequest,
      UserRoleEnum role) {
    String title = updateInquiryRequest.getTitle();
    String content = updateInquiryRequest.getContent();

    Inquiry inquiry = findById(id);
    isWriterAndIsManager(inquiry, username, role);
    inquiry.update(title, content);
    inquiryRepository.save(inquiry);


  }


  @Override
  public Result getInquiry(int page, int size, Direction direction,
      String properties) {
    Page<Inquiry> inquiryListPage = inquiryRepository.findAll(
        PageRequest.of(page - 1, size, direction, properties));
    return pageProcessing(inquiryListPage, page, size);
  }


  /**
   * 건당 문의 글 조회 시 회원만 조회가능, 비밀글은 해당 유저 및 관리자만 볼 수 있음
   *
   * @param id 문의글 아이디
   * @return 문의글 , 글에 해당하는 댓글, 대댓글
   */

  @Transactional(readOnly = true)
  @Override
  public InquiryResponse getSelectedInquiry(Long id, String nickName, UserRoleEnum role) {
    Inquiry inquiry = findById(id);
    if (inquiry.getSecret()) {
        // 건당 조회시 작성자 닉네임이 보이기위해 이것만 nickName 으로
      if (inquiry.isNickName(nickName) || role.equals(UserRoleEnum.MANAGER)) {
        //부모댓글를 조회하면 자식댓글도 함께 조회 됨
        List<ContactComment> parentComments = contactCommentService.findAllByInquiryIdAndParentIsNull(
            id);
        return new InquiryResponse(inquiry, parentComments);
      } else {
        throw new CustomException(ExceptionStatus.SECRET_POST);
      }
    } else {
      List<ContactComment> parentComments = contactCommentService.findAllByInquiryIdAndParentIsNull(
          id);
      return new InquiryResponse(inquiry, parentComments);
    }

  }


  @Override
  public Result searchByKeyword(String keyword, int page, int size,
      Direction direction, String properties) {
    String title = keyword;
    String content = keyword;

    Page<Inquiry> inquiryListPage = inquiryRepository.findAllByTitleContainingOrContentContaining(
        title, content, PageRequest.of(page - 1, size, direction, properties));
    return pageProcessing(inquiryListPage, page, size);
  }

  /**
   * 해당 유저 및 관리자만 글 삭제 가능
   *
   * @param id
   * @param username
   * @param role
   */
  @Transactional
  @Override
  public void deleteInquiry(Long id, String username, UserRoleEnum role) {
    Inquiry inquiry = findById(id);
    isWriterAndIsManager(inquiry, username, role);
    inquiryRepository.delete(inquiry);
    // 문의글 해당 댓글 삭제
    contactCommentService.deleteAllByInquiryId(id);


  }

  //해당 글의 유저이거나 역할이 매니저라면 true 반환 하는 메서드
  public void isWriterAndIsManager(Inquiry inquiry, String username, UserRoleEnum role) {
    if (!inquiry.isWriter(username) && !role.equals(UserRoleEnum.MANAGER)) {
      throw new CustomException(ExceptionStatus.WRONG_USER_T0_CONTACT);
    }
  }


  //해당 유저의 문의글 찾는 메서드
  @Override
  public Inquiry findById(Long inquiryId) {
    Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(
        () -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST)
    );
    return inquiry;
  }

  //페이지 처리를 위한 메서드
  public Result pageProcessing(Page<Inquiry> inquiryListPage, int page, int size) {
    int totalCount = (int) inquiryListPage.getTotalElements();
    if (inquiryListPage.isEmpty()) {
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


