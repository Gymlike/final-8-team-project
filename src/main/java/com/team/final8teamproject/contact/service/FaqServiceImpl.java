package com.team.final8teamproject.contact.service;


import com.team.final8teamproject.contact.Repository.FaqRepository;
import com.team.final8teamproject.contact.dto.FaqRequest;
import com.team.final8teamproject.contact.dto.FaqResponse;
import com.team.final8teamproject.contact.dto.UpdateFaqRequest;
import com.team.final8teamproject.contact.entity.Faq;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

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


  /**
   * 페이징 ui 처리에 필요한 데이터
   * page : 원하는 페이지
   * totalCount : 총 게시물 수
   * countPage :  한 화면에 보여줄 페이지 번호 수
   * startPage : 현재 화면에 보여줄 첫번째 페이지 번호 수
   * endPage : 현재 화면에 보여줄 마지막 페이지 번호 수
   * totalPage : 총 페이지 수
   *    ㄴ 원하는 페이지가 총페이지수보다 크다면 page = totalPage 치환;
   *    ㄴ endPage 가 총페이지 수 보다 크다면 endPage = totalPage 치환;
   */
  //페이지 처리를 위한 메서드
  //페이지 유지하면서 dto 반환 시키기(List<> 타입이 아닌 Page<>로 반환함으로 페이징 정보를 이용하기 위함
  public Result pageProcessing(Page<Faq> faqListPage, int page, int size) {
    int totalCount = (int) faqListPage.getTotalElements();
    if (faqListPage.isEmpty()) {
      throw new CustomException(ExceptionStatus.POST_IS_EMPTY);
    }

    Page<FaqResponse> faqResponses = faqListPage.map(FaqResponse::new);

    int countList = size;
    int countPage = 10;

    int totalPage = faqListPage.getTotalPages();

    if (totalPage < page) {
      page = totalPage;
    }

    int startPage = ((page-1)/10)*10 +1;
    int endPage = startPage + countPage -1 ;

    if( endPage > totalPage){
      endPage = totalPage;
    }


    return new Result(page, totalCount, countPage,totalPage, faqResponses);
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class Result<T> {
    private int page;
    private int totalCount;
    private int countPage;
    private int totalPage;

    private T data;

    public Result(int page, int totalCount, int countPage, int totalPage,
        T data) {
      this.page = page;
      this.totalCount = totalCount;
      this.countPage = countPage;
      this.totalPage = totalPage;

      this.data = data;
    }
  }
}



