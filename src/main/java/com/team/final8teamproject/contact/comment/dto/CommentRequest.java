package com.team.final8teamproject.contact.comment.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class CommentRequest {

  private final String comment;
  private final Long parent_id;
  private final String username;

  @Builder
  public CommentRequest(String comment, Long parent_id, String username) {
    this.comment = comment;
    this.parent_id = parent_id;
    this.username = username;
  }

  /* Dto -> Entity*/

}
