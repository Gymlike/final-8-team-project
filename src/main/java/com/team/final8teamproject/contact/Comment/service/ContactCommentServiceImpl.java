package com.team.final8teamproject.contact.Comment.service;

import static com.team.final8teamproject.contact.entity.QInquiry.inquiry;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.contact.Comment.dto.CreateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.dto.UpdateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import com.team.final8teamproject.contact.Comment.repository.ContactCommentRepository;
import com.team.final8teamproject.contact.Repository.InquiryRepository;
import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import com.team.final8teamproject.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactCommentServiceImpl implements ContactCommentService {

  private final ContactCommentRepository contactCommentRepository;
  private final InquiryRepository inquiryRepository;

  /**
   * ㄴ 부모댓글이 있는 경우    -> 대댓글 저장 ㄴ 자식댓글(대댓글) 경우     -> 대대댓글을 저장 ㄴ 부모댓글이 없는 경우.   -> 댓글 저장
   * <p>
   * 프론트엔드를 고려하여 자식 댓글의 계층구조로 보여주기 위해  depth 로 계층구조 나눔
   */

  @Override
  @Transactional
  public void saveInquiryComment(Long inquiryId,
      CreateContactCommentRequest createContactCommentRequest,
      String username, String nickName) {

    if (!inquiryRepository.existsById(inquiryId)) {
      throw new CustomException(ExceptionStatus.BOARD_NOT_EXIST);
    } else {
      /**부모댓글이 있는 경우 - 대댓글. 즉 자식 댓글이 됨
       * 자식 댓글인 경우 -대대댓글  저장
       */
      ContactComment parent = null;
      if (createContactCommentRequest.getParentId() != null) {
        parent = contactCommentRepository.findById(createContactCommentRequest.getParentId())
            .orElseThrow(
                () -> new CustomException(ExceptionStatus.COMMENT_NOT_EXIST)
            );
        /** 부모 댓글과 자식 댓글의 게시글 아이디가 같은지 확인*/
        //v2 객체 지향 entity 에게 역할을 줌


        int depth = parent.getDepth() + 1; //  자식댓글의 depth 설정 : 부모댓글의 자식 댓글이므로 + 1 함
        Inquiry inquiry = findInquiryById( inquiryId);
        ContactComment contactComment = createContactCommentRequest.toEntity(inquiry, username,
            nickName, parent, depth);
        contactComment.setParent(parent); // parent = null 값이므로 현재 부모댓글을 지정해줌
        contactCommentRepository.save(contactComment);

        /**부모댓글이 없는 경우 - 댓글 등록*/
      } else {
        int depth = 1;
        Inquiry inquiry = findInquiryById( inquiryId);
        ContactComment contactComment = createContactCommentRequest.toEntity(inquiry, username,
            nickName, parent, depth);
        contactCommentRepository.save(contactComment);
      }
    }
  }

  public Inquiry findInquiryById(Long inquiryId) {
    return inquiryRepository.findById(inquiryId).orElseThrow(
        ()-> new CustomException(ExceptionStatus.POST_IS_EMPTY)
    );
  }

  @Override
  @Transactional
  public void updateInquiryComment(Long commentId, UpdateContactCommentRequest updateCommentRequest,
      String username, UserRoleEnum role) {

    String comments = updateCommentRequest.getComments();
    ContactComment comment = findById(commentId);
    isWriterAndIsManager(comment, username, role);
    comment.update(comments);
    contactCommentRepository.save(comment);

  }

  @Transactional
  @Override
  public void deleteInquiryComment(Long commentId, String username, UserRoleEnum role) {
    ContactComment comment = findById(commentId);
    isWriterAndIsManager(comment, username, role);
    contactCommentRepository.deleteById(commentId);
  }

  /**
   * 해당 문의글 의 댓글 삭제하는 서비스 호출
   */

  public void deleteAllByInquiryId(Long inquiryId) {
    contactCommentRepository.deleteAllByInquiryId(inquiryId);
  }

  /**
   * 문의글에 해당하는 댓글 리스트 같이 반환하는 서비스 호출
   */
  @Override
  public List<ContactComment> findAllByInquiryIdAndParentIsNull(Long inquiryId) {
    return contactCommentRepository.findAllByInquiryIdAndParentIsNull(inquiryId);
  }

  /**
   * 해당 글의 유저이거나 역할이 매니저라면 true 반환 하는 메서드
   */
  public void isWriterAndIsManager(ContactComment comment, String username, UserRoleEnum role) {
    if (!comment.isWriter(username) && !role.equals(UserRoleEnum.MANAGER)) {
      throw new CustomException(ExceptionStatus.WRONG_USER_T0_COMMENT);
    }
  }

  /**
   * 해당 유저의 댓글 조회 메서드
   */
  public ContactComment findById(Long commentId) {
    ContactComment comment = contactCommentRepository.findById(commentId).orElseThrow(
        () -> new CustomException(ExceptionStatus.COMMENT_NOT_EXIST)
    );
    return comment;
  }

}


