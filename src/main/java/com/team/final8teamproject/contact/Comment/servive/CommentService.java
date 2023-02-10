package com.team.final8teamproject.contact.Comment.servive;


import com.team.final8teamproject.contact.Comment.dto.CommentRequest;


public interface CommentService {


  void InquiryComment(CommentRequest commentRequest, Long inquiryId, String username);
}
