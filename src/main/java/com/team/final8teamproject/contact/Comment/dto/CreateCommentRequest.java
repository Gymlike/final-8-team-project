package com.team.final8teamproject.contact.Comment.dto;


import com.team.final8teamproject.contact.Comment.entity.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class CreateCommentRequest {

  private final String comments;
  private final Long parentId;

  @Builder
  public CreateCommentRequest(String comments,Long parentId) {
    this.comments = comments;
    this.parentId = parentId;
  }

  public Comment toEntity(Long inquiryId, String username, Comment parent) {
    return Comment.builder()
        .comments(comments)
        .username(username)
        .inquiryId(inquiryId)
        .parent(parent)
        .build();
  }
}
