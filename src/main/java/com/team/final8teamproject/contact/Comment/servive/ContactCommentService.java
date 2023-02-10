package com.team.final8teamproject.contact.Comment.servive;


import com.team.final8teamproject.contact.Comment.dto.ContactCommentResponse;
import com.team.final8teamproject.contact.Comment.dto.CreateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.dto.UpdateContactCommentRequest;
import java.util.List;

public interface ContactCommentService {


  void saveInquiryComment(Long id, CreateContactCommentRequest createContactCommentRequest, String username);

  void updateInquiryComment(Long commentId, UpdateContactCommentRequest updateCommentRequest,
      String username);
  void deleteInquiryComment(Long commentId, String username);

}
