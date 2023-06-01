package com.team.final8teamproject.contact.dto;

import com.team.final8teamproject.contact.entity.Inquiry;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class UpdateInquiryRequest {

  private final String title;
  private final String content;

  public UpdateInquiryRequest(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public Inquiry toEntity(String username) {
    return Inquiry.builder()
        .title(title)
        .content(content)
        .username(username)
        .build();
  }
}
