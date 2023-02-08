package com.team.final8teamproject.contact.service;

import com.team.final8teamproject.contact.Repository.FaqRepository;
import com.team.final8teamproject.contact.dto.FaqRequest;
import com.team.final8teamproject.contact.dto.FaqResponse;
import com.team.final8teamproject.contact.entity.Faq;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

  private final FaqRepository faqRepository;

  @Override
  @Transactional
  public void saveFaq(FaqRequest faqRequest, Long managerId) {
    Faq faq = faqRequest.toEntity(managerId);
    faqRepository.save(faq);
  }

 //FAQ 전체 조회 (보기)
  @Override
  @Transactional(readOnly = true)
  public List<FaqResponse> getFaqList(int page, int size, Direction direction, String properties) {
    Page<Faq> faqListPage = faqRepository.findAll(PageRequest.of(page-1,size,direction,properties));
    if(faqListPage.isEmpty()){
      throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
    }
    List<FaqResponse> faqResponses = faqListPage.stream().map(FaqResponse::new).collect(Collectors.toList());
    return faqResponses;
  }

  //FAQ 해당 글 조회 (보기,가져오기)
  @Override
  @Transactional(readOnly = true)
  public FaqResponse getSelectedFaq(Long id) {
    Faq faq = faqRepository.findById(id).orElseThrow(
        ()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
    );
    return new FaqResponse(faq);
  }
  @Override
  @Transactional(readOnly = true)
  public List<FaqResponse> searchByKeyword(String keyword, int page, int size,
      Direction direction, String properties) {
    String question = keyword;
    String answer = keyword;
    Page<Faq> faqListPage = faqRepository.findAllBySearch(question,answer,PageRequest.of(page-1,size,direction,properties));
    List<FaqResponse> faqResponses = faqListPage.stream().map(FaqResponse::new).toList();
    return faqResponses;
  }

  @Override
  @Transactional
  public void deleteFaq(Long id, Long managerId) {
    Faq faq = faqRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 문의 글이 존재하지 않습니다.")
    );
    if (faq.getManagerId().equals(managerId)) {
      faqRepository.delete(faq);
    } else {
      throw new IllegalArgumentException("접근 할 수 있는 권한이 없습니다.");
    }
  }

}



