package com.team.final8teamproject.contact.Comment.service;


import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.contact.Comment.dto.CreateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.dto.UpdateContactCommentRequest;
import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import java.util.List;

public interface ContactCommentService {

  void saveInquiryComment(Long inquiryId,
      CreateContactCommentRequest createContactCommentRequest,
      String username,String nickName);

  void updateInquiryComment(Long commentId, UpdateContactCommentRequest updateCommentRequest,
      String username,UserRoleEnum role);

  void deleteInquiryComment(Long commentId, String username, UserRoleEnum role);

//  void deleteManager(Long id);

  List<ContactComment> findAllByInquiryIdAndParentIsNull(Long inquiryId);

}
