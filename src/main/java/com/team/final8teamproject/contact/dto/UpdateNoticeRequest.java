package com.team.final8teamproject.contact.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)

public class UpdateNoticeRequest {
  private final String title;
  private final String content;
  private final String imageUrl;


  public UpdateNoticeRequest(String title, String content,String imageUrl) {
    this.title = title;
    this.content = content;
    this.imageUrl = imageUrl;

  }
}
