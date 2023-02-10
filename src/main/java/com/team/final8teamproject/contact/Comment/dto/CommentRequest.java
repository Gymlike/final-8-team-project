package com.team.final8teamproject.contact.Comment.dto;


import com.team.final8teamproject.contact.Comment.entity.Comment;
import com.team.final8teamproject.contact.entity.Inquiry;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class CommentRequest {

  private final String comment;
  private final Long parentId;
//  private final String username;

  @Builder
  public CommentRequest(String comment, Long parentId) {
    this.comment = comment;
    this.parentId = parentId;
  }
  /* Dto -> Entity*/
  public Comment toEntity(Inquiry inquiry, String username, Comment parent) {
    return Comment.builder()
        .comment(comment)
        .inquiry(inquiry)
        .username(username)
        .parent(parent)
        .mainInquiryId(inquiry.getId())
        .build();
  }





}
