package com.team.final8teamproject.contact.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.final8teamproject.contact.Comment.dto.ContactCommentResponse;
import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import com.team.final8teamproject.contact.entity.Inquiry;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class InquiryResponse {

  private final String title;
  private final String content;
  private final String username;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  private final LocalDateTime createdDate;
  //private final LocalDateTime modifiedDate;
  private List<ContactCommentResponse> comments;

  public InquiryResponse(Inquiry inquiry, List<ContactComment> comments) {
    this.title = inquiry.getTitle();
    this.content = inquiry.getContent();
    this.username = inquiry.getUsername();
    this.createdDate = inquiry.getCreatedDate();
    // this.modifiedDate = inquiry.getModifiedDate();
    this.comments = comments.stream().map(ContactCommentResponse::new)
        .sorted(Comparator.comparing(ContactCommentResponse::getCreatedDate))
        .collect(Collectors.toList());

  }

  public InquiryResponse(Inquiry inquiry) {
    this.title = inquiry.getTitle();
    this.content = inquiry.getContent();
    this.username = inquiry.getUsername();
    this.createdDate = inquiry.getCreatedDate();
  //  this.modifiedDate = inquiry.getModifiedDate();
  }
}
