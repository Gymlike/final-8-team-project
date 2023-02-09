package com.team.final8teamproject.contact.dto;


import com.team.final8teamproject.contact.entity.Notice;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class NoticeRequest {

  private final String title;
  private final String content;

  @Builder
  public NoticeRequest(String title, String content) {
    this.title = title;
    this.content = content;

  }

  public Notice toEntity(Long managerId) {
    return Notice.builder()
        .managerId(managerId)
        .title(title)
        .content(content)
        .build();

  }


}