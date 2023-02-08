package com.team.final8teamproject.contact.comment.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public  CommentRequest(String comment, Long parentId) {

  @Builder
  public CommentRequest {
  }

  /* Dto -> Entity*/

}
