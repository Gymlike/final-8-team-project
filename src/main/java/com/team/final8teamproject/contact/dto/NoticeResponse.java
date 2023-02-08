package com.team.final8teamproject.contact.dto;

import com.team.final8teamproject.contact.entity.Notice;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true,access = AccessLevel.PROTECTED)
public class NoticeResponse {
 // private final Long managerId;
  private final String title;
  private final String content;


  public NoticeResponse(Notice notice) {
    this.title = notice.getTitle();
    this.content = notice.getContent();
  }
}
