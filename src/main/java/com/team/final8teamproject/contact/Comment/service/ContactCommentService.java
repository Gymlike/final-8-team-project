package com.team.final8teamproject.contact.Comment.service;


import com.team.final8teamproject.contact.Comment.dto.CreateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.dto.UpdateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import java.util.List;

public interface ContactCommentService {

  void saveInquiryComment(Long inquiryId,
      CreateContactCommentRequest createContactCommentRequest,
      String username);

  void updateInquiryComment(Long commentId, UpdateContactCommentRequest updateCommentRequest,
      String username);

  void deleteInquiryComment(Long commentId, String username);

  void deleteManager(Long id);

  List<ContactComment> findAllByInquiryIdAndParentIsNull(Long inquiryId);

}
