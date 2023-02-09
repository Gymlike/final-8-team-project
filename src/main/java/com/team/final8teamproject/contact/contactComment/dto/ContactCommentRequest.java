package com.team.final8teamproject.contact.contactComment.dto;


import com.team.final8teamproject.contact.contactComment.entity.ContactComment;
import com.team.final8teamproject.contact.entity.Inquiry;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class ContactCommentRequest {

  private final String comment;
  private final Long parentId;
//  private final String username;

  @Builder
  public ContactCommentRequest(String comment, Long parentId) {
    this.comment = comment;
    this.parentId = parentId;
  }
  /* Dto -> Entity*/
  public ContactComment toEntity(Inquiry inquiry, String username, ContactComment parent) {
    return ContactComment.builder()
        .comment(comment)
        .inquiry(inquiry)
        .username(username)
        .parent(parent)
        .mainInquiryId(inquiry.getId())
        .build();
  }





}
