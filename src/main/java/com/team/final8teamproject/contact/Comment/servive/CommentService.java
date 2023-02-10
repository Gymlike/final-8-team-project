package com.team.final8teamproject.contact.contactComment.servive;


import com.team.final8teamproject.contact.contactComment.dto.CommentRequest;


public interface CommentService {


  void InquiryComment(CommentRequest commentRequest, Long inquiryId, String username);
}
