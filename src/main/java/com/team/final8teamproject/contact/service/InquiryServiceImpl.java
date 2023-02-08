package com.team.final8teamproject.contact.service;

import com.team.final8teamproject.contact.Repository.InquiryRepository;
import com.team.final8teamproject.contact.dto.FaqResponse;
import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.InquiryResponse;
import com.team.final8teamproject.contact.entity.Faq;
import com.team.final8teamproject.contact.entity.Inquiry;
import java.util.List;
import java.util.stream.Collectors;
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



  @Transactional
  @Override
  public void createInquiry(InquiryRequest inquiryRequest, Long userId) {
    Inquiry inquiry = inquiryRequest.toEntity(userId);
    inquiryRepository.save(inquiry);
  }


  @Transactional
  @Override
  public void updateInquiry(Long id, Long userId, InquiryRequest inquiryRequest) {
    Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 문의 글이 존재하지 않습니다.")
    );
    if (inquiry.getUserId().equals(userId)) {
      inquiry.update(inquiryRequest);
      inquiryRepository.save(inquiry);
    } else {
      throw new IllegalArgumentException("접근 할 수 있는 권한이 없습니다.");
    }
  }


  @Transactional(readOnly = true)
  @Override
  public List<InquiryResponse> getInquiry(int page, int size, Direction direction,
      String properties) {
    Page<Inquiry> inquiryListPage = inquiryRepository.findAll(PageRequest.of(page-1,size,direction,properties));
    List<InquiryResponse> inquiryResponses = inquiryListPage.stream().map(InquiryResponse::new)
        .toList();
   return inquiryResponses;
  }

  @Transactional(readOnly = true)
  @Override
  public  InquiryResponse getSelectedInquiry(Long id) {
    Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(
        ()-> new IllegalArgumentException("해당 문의 글이 존재하지 않습니다.")
    );
    return new InquiryResponse(inquiry);
  }

  @Transactional(readOnly = true)
  @Override
  public List<InquiryResponse> searchByKeyword(String keyword, int page, int size, Direction direction,
      String properties) {
      String title = keyword;
      String content = keyword;
      Page<Inquiry> inquiryListPage = inquiryRepository.findAllBySearch(title,content,PageRequest.of(page-1,size,direction,properties));
      List<InquiryResponse> inquiryResponses = inquiryListPage.stream().map(InquiryResponse::new).toList();
      return inquiryResponses;
    }


  //todo  관리자도 삭제가능 어떻게???
  @Transactional
  @Override
  public void deleteInquiry(Long id, Long userIdOrManagerId) {
    Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 문의 글이 존재 하지 않습니다.")
    );
    if (inquiry.getUserId().equals(userIdOrManagerId)) {
      inquiryRepository.delete(inquiry);
    } else {
      throw new IllegalArgumentException("접근 할 수  있는 권한이 없습니다.");
    }
  }

  //todo 관리자따로, 유저따로 삭제 가능하게 하기

  @Transactional
  @Override
  public void deleteManager(Long id) {
    Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 문의 글이 존재 하지 않습니다.")
    );
      inquiryRepository.delete(inquiry);
  }
}


