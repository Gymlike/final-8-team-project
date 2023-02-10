package com.team.final8teamproject.contact.service;

import com.team.final8teamproject.contact.Repository.InquiryRepository;

import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.InquiryResponse;
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



  @Transactional
  @Override
  public void createInquiry(InquiryRequest inquiryRequest, String username) {
    Inquiry inquiry = inquiryRequest.toEntity(username);
    inquiryRepository.save(inquiry);
  }


  @Transactional
  @Override
  public void updateInquiry(Long id, String username, InquiryRequest inquiryRequest) {
    String title = inquiryRequest.getTitle();
    String content = inquiryRequest.getContent();

    Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 문의 글이 존재하지 않습니다.")
    );
    if (inquiry.getUsername().equals(username)) {
      inquiry.update(title,content);
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
      Page<Inquiry> inquiryListPage = inquiryRepository.findAllByTitleContainingOrContentContaining(title,content,PageRequest.of(page-1,size,direction,properties));
      List<InquiryResponse> inquiryResponses = inquiryListPage.stream().map(InquiryResponse::new).toList();
      return inquiryResponses;
    }



  @Transactional
  @Override
  public void deleteInquiry(Long id, String username) {
    Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 문의 글이 존재 하지 않습니다.")
    );
    if (inquiry.getUsername().equals(username)) {
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

  public Inquiry findById(Long inquiryId){
      Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(
          ()-> new IllegalArgumentException(" 해당 문의 글이 존재하지 않습니다.")
      );
      return inquiry;
  }

  public Boolean existsById(Long inquiryId){
     return inquiryRepository.existsById(inquiryId);
  }
}


