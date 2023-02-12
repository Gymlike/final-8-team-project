package com.team.final8teamproject.contact.Comment.dto;


import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class CreateContactCommentRequest {

  private final String comments;
  private final Long parentId;

  @Builder
  public CreateContactCommentRequest(String comments,Long parentId) {
    this.comments = comments;
    this.parentId = parentId;
  }

  public ContactComment toEntity(Long inquiryId, String username, ContactComment parent) {
    System.out.println(comments+"aa");
    return ContactComment.builder()
        .comments(comments)
        .username(username)
        .inquiryId(inquiryId)
        .parent(parent)
        .build();
  }
}
