package com.team.final8teamproject.contact.Comment.dto;


import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import com.team.final8teamproject.contact.entity.Inquiry;
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

  public ContactComment toEntity(Inquiry inquiry, String username,String nickName, ContactComment parent,int depth) {
    return ContactComment.builder()
        .comments(comments)
        .username(username)
        .nickName(nickName)
        .inquiry(inquiry)
        .parent(parent)
        .depth(depth)
        .build();
  }
}
