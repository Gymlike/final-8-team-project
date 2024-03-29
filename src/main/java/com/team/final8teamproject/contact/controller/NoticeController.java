package com.team.final8teamproject.contact.controller;

//import com.team.final8teamproject.contact.dto.InquiryResponse;

import com.team.final8teamproject.board.dto.ImageNameDTO;
import com.team.final8teamproject.contact.dto.NoticeRequest;
import com.team.final8teamproject.contact.dto.NoticeResponse;
import com.team.final8teamproject.contact.dto.UpdateNoticeRequest;
import com.team.final8teamproject.contact.service.NoticeService;
import com.team.final8teamproject.contact.service.NoticeServiceImpl.Result;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.share.aws_s3.PresignedUrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/** .requestMatchers("/api/managers/notices").hasAnyRole("Manager", "GeneralManager")
 * 글쓰기 권한 관리자 // 그 외 다른 유저들은 조회만 가능
 */
@RequiredArgsConstructor
@RequestMapping("/api/managers/notices")
@RestController
public class NoticeController {

  private final NoticeService noticeService;
  private  final PresignedUrlService presignedUrlService;
  private String path; //이미지파일 경로

  //관리자 공지사항 등록
  @PostMapping("")
  public ResponseEntity saveNotice(@RequestBody @Valid NoticeRequest noticeRequest,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    String imageUrl = presignedUrlService.findByName(path);
    noticeService.saveNotice(noticeRequest, userDetails.getBase().getId(),imageUrl);
    return ResponseEntity.ok("등록 완료");
    //new ResponseEntity<>("등록완료",HttpStatus.CREATED);
  }

  /**
   * S3에게 pre-signed URL (권한) 요청
   */
  @PostMapping("/presigned")
  public String createPresigned(@RequestBody ImageNameDTO imageNameDTO
      ) {
      path ="contact";  //원하는 경로 지정
      String imageName = imageNameDTO.getImageName();
      return presignedUrlService.getPreSignedUrl(path,imageName);
  }

  @GetMapping("/check")
  public Result getNoticeList(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "direction", required = false, defaultValue = "DESC") Direction direction,
      @RequestParam(value = "properties", required = false, defaultValue = "createdDate") String properties) {
    return noticeService.getNoticeList(page, size, direction, properties);
  }

  @GetMapping("/check/{id}")
  public NoticeResponse getSelectedNotice(@PathVariable Long id) {
    return noticeService.getSelectedNotice(id);
  }

  @GetMapping("/check/search")
  public Result searchByKeyword(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "direction", required = false, defaultValue = "DESC") Direction direction,
      @RequestParam(value = "properties", required = false, defaultValue = "createdDate") String properties) {
    return noticeService.searchByKeyword(keyword, page, size, direction, properties);

  }

  // 관리자 공지사항 수정
  @PutMapping("/{id}")
  public ResponseEntity updateNotice(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody UpdateNoticeRequest updateNoticeRequest) {
    String imageUrl = presignedUrlService.findByName(path);
    noticeService.updateNotice(id, userDetails.getBase().getId(), updateNoticeRequest,imageUrl);
    return ResponseEntity.ok("수정 완료");
  }


  //관리자 공지사항 삭제
  @DeleteMapping("/{id}")
  public ResponseEntity deleteNotice(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    noticeService.deleteNotice(id, userDetails.getBase().getId());
    return ResponseEntity.ok("삭제 완료");

  }

}
