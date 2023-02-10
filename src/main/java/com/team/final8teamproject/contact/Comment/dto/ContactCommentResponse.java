package com.team.final8teamproject.contact.Comment.dto;

import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;


@Getter
public class ContactCommentResponse {

  private final Long id;
  private final String username;
  private final List<ContactCommentResponse> children;
  private final LocalDateTime createdDate;
  private final LocalDateTime modifiedDate;

  public ContactCommentResponse(ContactComment contactComment) {
    this.id = contactComment.getId();
    this.username = contactComment.getUsername();
    this.createdDate = contactComment.getCreatedDate();
    this.modifiedDate = contactComment.getModifiedDate();
    this.children = contactComment.getChildren().stream().map(ContactCommentResponse::new)
        .sorted(Comparator.comparing(ContactCommentResponse::getCreatedDate)).collect(Collectors.toList());
  }
}
