package com.team.final8teamproject.contact.service;

import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import com.team.final8teamproject.contact.Comment.servive.ContactCommentServiceImpl;
import com.team.final8teamproject.contact.Repository.InquiryRepository;
import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.InquiryResponse;
import com.team.final8teamproject.contact.dto.UpdateInquiryRequest;
import com.team.final8teamproject.contact.entity.Inquiry;
import java.util.List;
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
  public void createInquiry(InquiryRequest inquiryRequest, String username) {
    Inquiry inquiry = inquiryRequest.toEntity(username);
    inquiryRepository.save(inquiry);
  }


  @Transactional
  @Override
  public void updateInquiry(Long id, String username, UpdateInquiryRequest updateInquiryRequest) {
    String title = updateInquiryRequest.getTitle();
    String content = updateInquiryRequest.getContent();

    Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 문의 글이 존재하지 않습니다.")
    );
    if(inquiry.isWriter(username)){
      inquiry.update(title, content);
      inquiryRepository.save(inquiry);
    } else {
      throw new IllegalArgumentException("접근 할 수 있는 권한이 없습니다.");
    }
  }


  @Transactional(readOnly = true)
  @Override
  public List<InquiryResponse> getInquiry(int page, int size, Direction direction,
      String properties) {
    Page<Inquiry> inquiryListPage = inquiryRepository.findAll(
        PageRequest.of(page - 1, size, direction, properties));
    List<InquiryResponse> inquiryResponses = inquiryListPage.stream().map(InquiryResponse::new)
        .toList();
    return inquiryResponses;
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
        () -> new IllegalArgumentException("해당 문의 글이 존재하지 않습니다."));
    // List<ContactComment> comments = contactCommentRepository.findAllByInquiryId(id);
    List<ContactComment> parentComments = contactCommentService.findAllByInquiryIdAndParentIsNull(
        id);
    return new InquiryResponse(inquiry, parentComments);
  }

  @Transactional(readOnly = true)
  @Override
  public List<InquiryResponse> searchByKeyword(String keyword, int page, int size,
      Direction direction, String properties) {

    String title = keyword;
    String content = keyword;

    Page<Inquiry> inquiryListPage = inquiryRepository.findAllByTitleContainingOrContentContaining(
        title, content, PageRequest.of(page - 1, size, direction, properties));
    List<InquiryResponse> inquiryResponses = inquiryListPage.stream().map(InquiryResponse::new)
        .toList();
    return inquiryResponses;
  }


  @Transactional
  @Override
  public void deleteInquiry(Long id, String username) {
    Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 문의 글이 존재 하지 않습니다.")
    );
    if (inquiry.isWriter(username)) {
      inquiryRepository.delete(inquiry);
      // 문의글 해당 댓글 삭제
      contactCommentService.deleteAllByInquiryId(id);
    } else {
      throw new IllegalArgumentException("접근 할 수  있는 권한이 없습니다.");
    }
  }


  /**
   * 관리자가 유저 글 삭제 기능
   */
  @Transactional
  @Override
  public void deleteManager(Long id) {
    Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 문의 글이 존재 하지 않습니다.")
    );
    inquiryRepository.delete(inquiry);
  }

  @Override
  public Inquiry findById(Long inquiryId) {
    Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(
        () -> new IllegalArgumentException(" 해당 문의 글이 존재하지 않습니다.")
    );
    return inquiry;
  }

}


