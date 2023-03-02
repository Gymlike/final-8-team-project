package com.team.final8teamproject.contact.service;


import com.team.final8teamproject.contact.Repository.NoticeRepository;
import com.team.final8teamproject.contact.dto.NoticeRequest;
import com.team.final8teamproject.contact.dto.NoticeResponse;
import com.team.final8teamproject.contact.dto.UpdateNoticeRequest;
import com.team.final8teamproject.contact.entity.Notice;
import com.team.final8teamproject.share.aws_s3.PresignedUrlService;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

  private final NoticeRepository noticeRepository;

  @Transactional
  @Override
  public void saveNotice(@Valid NoticeRequest noticeRequest, Long managerId,String imageUrl) {
    Notice notice = noticeRequest.toEntity(managerId,imageUrl);
    noticeRepository.save(notice);
  }


  /**
   * 프론트 페이징 시도 * int page, int countList, int countPage, int totalCount, T data
   */

  @Transactional(readOnly = true)
  @Override
  public Result getNoticeList(int page, int size, Direction direction,
      String properties) {

    Page<Notice> noticeListPage = noticeRepository.findAll(
        PageRequest.of(page - 1, size, direction, properties));
    int totalCount = (int) noticeListPage.getTotalElements();
    System.out.println("totalCount:"+totalCount);

    if (noticeListPage.isEmpty()) {
      throw new CustomException(ExceptionStatus.POST_IS_EMPTY);
    }
    List<NoticeResponse> noticeResponses = noticeListPage.stream().map(NoticeResponse::new).collect(
        Collectors.toList());
    int countList = size;
    int countPage = 5;//todo 리팩토링때  10으로 변경예정
    int totalPage = totalCount / countList;
    if (totalCount % countList > 0) {
      totalPage++;
    }
    if (totalPage < page) {
      page = totalPage;
    }
    return new Result(page, totalCount, countPage, totalPage, noticeResponses);
  }


  @Transactional(readOnly = true)
  @Override
  public NoticeResponse getSelectedNotice(Long id) {
    Notice notice = noticeRepository.findById(id).orElseThrow(
        () -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST)
    );
    return new NoticeResponse(notice);
  }
  @Transactional(readOnly = true)
  @Override
  public Result searchByKeyword(String keyword, int page, int size,
      Direction direction, String properties) {
    String title = keyword;
    String content = keyword;

    Page<Notice> noticeListPage = noticeRepository.findAllByTitleContainingOrContentContaining(
        title, content,
        PageRequest.of(page - 1, size, direction, properties));
    int totalCount = (int) noticeListPage.getTotalElements();
    if (noticeListPage.isEmpty()) {
      throw new CustomException(ExceptionStatus.POST_IS_EMPTY);
    }
    List<NoticeResponse> noticeResponses = noticeListPage.stream().map(NoticeResponse::new).collect(
        Collectors.toList());
    int countList = size;
    int countPage = 5;//todo 리팩토링때  10으로 변경예정
    int totalPage = totalCount / countList;
    if (totalCount % countList > 0) {
      totalPage++;
    }
    if (totalPage < page) {
      page = totalPage;
    }
    return new Result(page, totalCount, countPage, totalPage, noticeResponses);
  }

  @Transactional
  @Override
  public void updateNotice(Long id, Long managerId, UpdateNoticeRequest updateNoticeRequest) {
    String title = updateNoticeRequest.getTitle();
    String content = updateNoticeRequest.getContent();
    String imageUrl = updateNoticeRequest.getImageUrl();

    Notice notice = noticeRepository.findById(id).orElseThrow(
        () -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST)
    );
    if (notice.getManagerId().equals(managerId)) {
      notice.update(title, content, imageUrl);
      noticeRepository.save(notice);
    } else {
      throw new CustomException(ExceptionStatus.WRONG_USER_T0_CONTACT);
    }

  }

  @Transactional
  @Override
  public void deleteNotice(Long id, Long managerId) {
    Notice notice = noticeRepository.findById(id).orElseThrow(
        () -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST)
    );
    if (notice.getManagerId().equals(managerId)) {
      noticeRepository.delete(notice);
    } else {
      throw new CustomException(ExceptionStatus.WRONG_USER_T0_CONTACT);
    }

  }

  /**
   * page = 현재페이지 countList = 한 페이지에 출력 될 게시물 수 countPage = 한 화면에 출력 될 페이지수 totalCount = 총 게시물 수
   *
   * @param <T>
   */
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
