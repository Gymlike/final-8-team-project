package com.team.final8teamproject.contact.Comment.servive;


import com.team.final8teamproject.contact.Comment.dto.CreateCommentRequest;
import com.team.final8teamproject.contact.Comment.dto.UpdateCommentRequest;

public interface CommentService {


  void saveInquiryComment(Long id, CreateCommentRequest createCommentRequest, String username);

//  void updateInquiryComment(Long commentId, UpdateCommentRequest updateCommentRequest, String username);
//
//  void deleteInquiryComment(Long commentId, String username);
}
