package com.team.final8teamproject.contact.dto;

import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.contact.entity.Notice;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class InquiryRequest {
  private final String title;
  private final String content;

  @Builder
  public InquiryRequest(String title, String content) {
    this.title = title;
    this.content = content;

  }

  public Inquiry toEntity(Long userId) {
    return Inquiry.builder()
        .userId(userId)
        .title(title)
        .content(content)
        .build();
  }


}
