package com.team.final8teamproject.contact.Comment.servive;

import com.team.final8teamproject.contact.Comment.dto.ContactCommentResponse;
import com.team.final8teamproject.contact.Comment.dto.CreateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.dto.UpdateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import com.team.final8teamproject.contact.Comment.repository.ContactCommentRepository;
import com.team.final8teamproject.contact.Repository.InquiryRepository;
import com.team.final8teamproject.contact.service.InquiryServiceImpl;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.attoparser.dom.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactCommentServiceImpl implements ContactCommentService {

  private final ContactCommentRepository contactCommentRepository;
  private final InquiryRepository inquiryRepository;

  /**
   * 부모댓글이 있는 경우 . 자식댓글인 경우 (대댓글) 댓글에 댓글을 저장
   * <p>
   * 부모댓글이 없는 경우. 포스트에 댓글을 저장 // todo  문의사항에 관리자가 답변글 남기면 - > 답변글에  해당 글 주인이 다시 답글 할수 있어야해 .. 음 ..
   */

  @Override
  public void saveInquiryComment(Long inquiryId,
      CreateContactCommentRequest createContactCommentRequest,
      String username) {
    if (!inquiryRepository.existsById(inquiryId)) {
      throw new CustomException(ExceptionStatus.BOARD_NOT_EXIST);
    } else {
      /**부모댓글이 있는 경우 - 대댓글 등록. 즉 자식 댓글이 됨 */
      ContactComment parent = null;
      if (createContactCommentRequest.getParentId() != null) {
        parent = contactCommentRepository.findById(createContactCommentRequest.getParentId())
            .orElseThrow(
                () -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST)
            );

        /** 부모 댓글과 자식 댓글의 게시글 아이디가 같은지 확인*/
        if (!parent.getInquiryId().equals(inquiryId)) {
          throw new CustomException(ExceptionStatus.WRONG_POST_ID);
        }
        ContactComment contactComment = createContactCommentRequest.toEntity(inquiryId, username,
            parent);
        // ContactComment contactComment = new ContactComment(comments,inquiryId,username,parent);
        contactComment.getParent().setId(createContactCommentRequest.getParentId());
        contactCommentRepository.save(contactComment);

        /**부모댓글이 없는 경우 - 댓글 등록*/
      } else {
        //   ContactComment contactComment = new ContactComment(comments,inquiryId,username,parent);
        ContactComment contactComment = createContactCommentRequest.toEntity(inquiryId, username,
            parent);
        contactCommentRepository.save(contactComment);
      }
    }
  }

  @Override
  @Transactional
  public void updateInquiryComment(Long commentId, UpdateContactCommentRequest updateCommentRequest,
      String username) {
    String comments = updateCommentRequest.getComments();
    ContactComment comment = contactCommentRepository.findById(commentId).orElseThrow(
        () -> new CustomException(ExceptionStatus.COMMENT_NOT_EXIST)
    );
    if (comment.isWriter(username)) {
      comment.update(comments);
      contactCommentRepository.save(comment);
    } else {
      throw new CustomException(ExceptionStatus.ACCESS_DENINED);
    }
  }

  @Transactional
  @Override
  public void deleteInquiryComment(Long commentId, String username) {
    ContactComment comment = contactCommentRepository.findById(commentId).orElseThrow(
        () -> new CustomException(ExceptionStatus.COMMENT_NOT_EXIST)
    );
    //if (comment.getUsername().equals(username)) {
    if(comment.isWriter(username)){
      contactCommentRepository.deleteByUsername(username);
    } else {
      throw new CustomException(ExceptionStatus.ACCESS_DENINED);
    }
  }
  /** 해당 문의글 의 댓글 삭제하는 서비스 호출 */

  public void deleteAllByInquiryId(Long inquiryId){
    contactCommentRepository.deleteAllByInquiryId(inquiryId);
  }

  /**문의글에 해당하는 댓글 리스트 같이 반환하는 서비스 호출 */
  @Override
  public List<ContactComment> findAllByInquiryIdAndParentIsNull(Long inquiryId) {
    return contactCommentRepository.findAllByInquiryIdAndParentIsNull(inquiryId);
  }

}
