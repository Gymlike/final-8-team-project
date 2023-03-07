package com.team.final8teamproject.contact.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.final8teamproject.contact.entity.Notice;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true,access = AccessLevel.PROTECTED)
public class NoticeResponse {

  private final Long id;
  private final Long managerId;
  private final String title;
  private final String content;

  private final String image;
  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
  private final LocalDateTime createdDate;
  //private final LocalDateTime modifiedDate;



  public NoticeResponse(Notice notice) {
    this.id = notice.getId();
    this.managerId = notice.getManagerId();
    this.title = notice.getTitle();
    this.content = notice.getContent();
    this.createdDate = notice.getCreatedDate();
    this.image = notice.getImageUrl(); // 이미지 url
  //  this.modifiedDate = notice.getModifiedDate();
  }
}
