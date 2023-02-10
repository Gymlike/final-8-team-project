package com.team.final8teamproject.contact.contactComment.servive;

import com.team.final8teamproject.contact.contactComment.dto.CommentRequest;
import com.team.final8teamproject.contact.contactComment.entity.Comment;
import com.team.final8teamproject.contact.contactComment.repository.CommentRepository;
import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.contact.service.InquiryServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final InquiryServiceImpl inquiryServiceImpl;
  private final CommentRepository commentRepository;

  @Transactional
  @Override
  public void InquiryComment(CommentRequest commentRequest, Long inquiryId, String username) {
    Inquiry inquiry = inquiryServiceImpl.findById(inquiryId);

    Comment parent = null;
    //자식 댓글인 경우
    if(commentRequest.getParentId() != null){
      try {
        parent = (Comment) commentRepository.findById(commentRequest.getParentId()).orElseThrow(
            ()-> new IllegalArgumentException(" 해당 댓글이 존재 하지 않습니다.")
        );
      } catch (Throwable e) {
        throw new RuntimeException(e);
      }
      //부모 댓글과 자식 댓글의 문의글 아이디가 같은지 확인
      if(!parent.getMainInquiryId().equals(inquiryId)){
        throw new IllegalArgumentException("해당 글 번호가 일치하지 않습니다.");
      }
    //댓글인 경우
      if(parent ==null){
       // Comment comment = new Comment(commentRequest.toEntity(inquiryId,username,parent));
        Comment comment = commentRequest.toEntity(inquiry,username,parent);
        commentRepository.save(comment);
      }
      else {//대댓글인 경우
      Comment comment = commentRequest.toEntity(inquiry,username,parent );
      comment.setMainInquiryId(inquiry.getId());
      commentRepository.save(comment);
      }


    }
  }
}
