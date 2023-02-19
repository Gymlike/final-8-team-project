package com.team.final8teamproject.contact.Comment.servive;


import com.team.final8teamproject.contact.Comment.dto.ContactCommentResponse;
import com.team.final8teamproject.contact.Comment.dto.CreateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.dto.UpdateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import java.util.List;

public interface ContactCommentService {


  void saveInquiryComment(Long id, CreateContactCommentRequest createContactCommentRequest, String username, String nickName);

  void updateInquiryComment(Long commentId, UpdateContactCommentRequest updateCommentRequest,
      String username,String nickName);
  void deleteInquiryComment(Long commentId, String username);
  void deleteManager(Long id);
  List<ContactComment> findAllByInquiryIdAndParentIsNull(Long inquiryId);

}
