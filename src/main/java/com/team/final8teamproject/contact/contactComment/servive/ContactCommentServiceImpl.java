//package com.team.final8teamproject.contact.contactComment.servive;
//
//import com.team.final8teamproject.contact.contactComment.dto.ContactCommentRequest;
//import com.team.final8teamproject.contact.contactComment.entity.ContactComment;
//import com.team.final8teamproject.contact.contactComment.repository.ContactCommentRepository;
//import com.team.final8teamproject.contact.entity.Inquiry;
//import com.team.final8teamproject.contact.service.InquiryServiceImpl;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class ContactCommentServiceImpl implements ContactCommentService {
//
//  private final InquiryServiceImpl inquiryServiceImpl;
//  private final ContactCommentRepository contactCommentRepository;
//
//  @Transactional
//  @Override
//  public void InquiryComment(ContactCommentRequest contactCommentRequest, Long inquiryId, String username) {
//    Inquiry inquiry = inquiryServiceImpl.findById(inquiryId);
//
//    ContactComment parent = null;
//    //자식 댓글인 경우
//    if(contactCommentRequest.getParentId() != null){
//      try {
//        parent = (ContactComment) contactCommentRepository.findById(contactCommentRequest.getParentId()).orElseThrow(
//            ()-> new IllegalArgumentException(" 해당 댓글이 존재 하지 않습니다.")
//        );
//      } catch (Throwable e) {
//        throw new RuntimeException(e);
//      }
//      //부모 댓글과 자식 댓글의 문의글 아이디가 같은지 확인
//      if(!parent.getMainInquiryId().equals(inquiryId)){
//        throw new IllegalArgumentException("해당 글 번호가 일치하지 않습니다.");
//      }
//    //댓글인 경우
//      if(parent ==null){
//       // Comment comment = new Comment(commentRequest.toEntity(inquiryId,username,parent));
//        ContactComment contactComment = contactCommentRequest.toEntity(inquiry,username,parent);
//        contactCommentRepository.save(contactComment);
//      }
//      else {//대댓글인 경우
//      ContactComment contactComment = contactCommentRequest.toEntity(inquiry,username,parent );
//      contactComment.setMainInquiryId(inquiry.getId());
//      contactCommentRepository.save(contactComment);
//      }
//
//
//    }
//  }
//}
