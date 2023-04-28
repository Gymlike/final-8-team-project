package com.team.final8teamproject.contact.service;

import com.team.final8teamproject.contact.Repository.FaqRepository;
import com.team.final8teamproject.contact.dto.FaqRequest;
import com.team.final8teamproject.contact.dto.FaqResponse;
import com.team.final8teamproject.contact.dto.InquiryResponse;
import com.team.final8teamproject.contact.dto.UpdateFaqRequest;
import com.team.final8teamproject.contact.entity.Faq;
import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.contact.service.InquiryServiceImpl.Result;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

  private final FaqRepository faqRepository;

  @Override
  public void saveFaq(@Valid FaqRequest faqRequest, Long managerId) {
    Faq faq = faqRequest.toEntity(managerId);
    faqRepository.save(faq);
  }

  //FAQ 전체 조회 (보기)
  @Override
  public Result getFaqList(int page, int size, Direction direction, String properties) {
    Page<Faq> faqListPage = faqRepository.findAll(
        PageRequest.of(page - 1, size, direction, properties));
    return pageProcessing(faqListPage, page, size);
  }


  //FAQ 해당 글 조회 (보기,가져오기)
  @Override
  public FaqResponse getSelectedFaq(Long id) {
    Faq faq = findByFaqId(id);
    return new FaqResponse(faq);
  }

  @Override
  public Result searchByKeyword(String keyword, int page, int size,
      Direction direction, String properties) {
    String question = keyword;
    String answer = keyword;
    Page<Faq> faqListPage = faqRepository.findAllByQuestionContainingIgnoreCaseOrAnswerContainingIgnoreCase(question,
        answer, PageRequest.of(page - 1, size, direction, properties));
    return pageProcessing(faqListPage, page, size);

  }

  @Override
  public void updateFaq(Long id, Long managerId, UpdateFaqRequest updateFaqRequest) {
    String question = updateFaqRequest.getQuestion();
    String answer = updateFaqRequest.getAnswer();

    Faq faq = findByFaqId(id);
    faq.isWriter(managerId);
    faq.update(question, answer);
    faqRepository.save(faq);
  }

  @Override
  public void deleteFaq(Long id, Long managerId) {

    Faq faq = findByFaqId(id);
    faq.isWriter(managerId);
    faqRepository.delete(faq);
  }

  private Faq findByFaqId(Long id){
    return faqRepository.findById(id).orElseThrow(
            () -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST)
    );
  }

  //페이지 처리를 위한 메서드
  public Result pageProcessing(Page<Faq> faqListPage, int page, int size) {
    int totalCount = (int) faqListPage.getTotalElements();
    if (faqListPage.isEmpty()) {
      throw new CustomException(ExceptionStatus.POST_IS_EMPTY);
    }
    List<FaqResponse> faqResponses = faqListPage.stream().map(FaqResponse::new)
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
    return new Result(page, totalCount, countPage, totalPage, faqResponses);
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class Result<T> {
    private int page;
    private int totalCount;
    private int countPage;
    private int totalPage;
    private T data;


    public Result(int page, int totalCount, int countPage, int totalPage, T data) {
      this.page = page;
      this.totalCount = totalCount;
      this.countPage = countPage;
      this.totalPage = totalPage;
      this.data = data;
    }
  }
}



