package com.team.final8teamproject.contact.Comment.servive;

import com.ibm.cuda.CudaException;
import com.team.final8teamproject.contact.Comment.dto.CreateCommentRequest;
import com.team.final8teamproject.contact.Comment.dto.UpdateCommentRequest;
import com.team.final8teamproject.contact.Comment.entity.Comment;
import com.team.final8teamproject.contact.Comment.repository.CommentRepository;
import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.contact.service.InquiryServiceImpl;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final InquiryServiceImpl inquiryService;

  //댓글 등록
  @Transactional
  @Override
  public void saveInquiryComment(Long inquiryId, CreateCommentRequest createCommentRequest,
      String username) {
    if (!inquiryService.existsById(inquiryId)) {
      throw new CustomException(ExceptionStatus.BOARD_NOT_EXIST);
    } else {

      /**자식 댓글인 경우*/
      Comment parent = null;
      if (createCommentRequest.getParentId() != null) {
        parent = commentRepository.findById(createCommentRequest.getParentId()).orElseThrow(
            () -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST)
        );
        // 부모 댓글과 자식 댓글의 게시글 아이디가 같은지 확인
        if (!parent.getInquiryId().equals(inquiryId)) {
          throw new CustomException(ExceptionStatus.WRONG_POST_ID);
        }
      }
        Comment comment = createCommentRequest.toEntity(inquiryId, username, parent);
        commentRepository.save(comment);

//      /**댓글인 경우*/
//      if (parent == null) {
//        Comment comment = createCommentRequest.toEntity(inquiryId, username, parent);
//        commentRepository.save(comment);
//        /** 대댓글인 경우*/
//      } else {
//        Comment comment = createCommentRequest.toEntity(inquiryId, username, parent);//parent 부모댓글
//        // comment.getParent().setId(commentRequestDto.getParentId());
//        // comment.getParent().setId(createCommentRequest.getParentId());
//        commentRepository.save(comment);
//      }


    }
  }
//
//  }
//  @Transactional
//  @Override
//  public void updateInquiryComment(Long commentId, UpdateCommentRequest updateCommentRequest,
//      String username) {
//    String comments = updateCommentRequest.getComments();
//    Comment comment = commentRepository.findById(commentId).orElseThrow(
//        ()-> new CustomException(ExceptionStatus.COMMENT_NOT_EXIST)
//    );
//    if(comment.getUsername().equals(username)){
//      comment.update(comments);
//      commentRepository.save(comment);
//    }else{
//      throw new CustomException(ExceptionStatus.ACCESS_DENINED);
//    }
//  }
//  @Transactional
//  @Override
//  public void deleteInquiryComment(Long commentId, String username) {
//    Comment comment = commentRepository.findById(commentId).orElseThrow(
//        ()-> new CustomException(ExceptionStatus.COMMENT_NOT_EXIST)
//    );
//    if(comment.getUsername().equals(username)){
//      commentRepository.deleteByUsername(username);
//    }else{
//      throw new CustomException(ExceptionStatus.ACCESS_DENINED);
//    }
//  }


}
