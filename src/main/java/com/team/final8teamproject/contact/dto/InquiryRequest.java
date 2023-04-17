package com.team.final8teamproject.contact.dto;

import com.team.final8teamproject.contact.entity.Inquiry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class InquiryRequest {
  @NotBlank // null 과 "" 과 " " 모두 비허용
  private final String title;
  @NotBlank
  private final String content;
  private boolean secret; // 비밀글 유무

  @Builder
  public InquiryRequest(String title, String content,Boolean secret) {
    this.title = title;
    this.content = content;
    this.secret = secret;
  }

  public Inquiry toEntity(String username,String nickName) {
    return Inquiry.builder()
        .title(title)
        .content(content)
        .username(username)
        .nickName(nickName)
        .secret(secret) //defalt = false, true 이면 비밀글 처리됨
        .build();
  }


}
