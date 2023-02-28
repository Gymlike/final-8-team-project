package com.team.final8teamproject.contact.dto;

import com.team.final8teamproject.contact.entity.Inquiry;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class InquiryRequest {
  private final String title;
  private final String content;
  private boolean secretCheckBox = false; //todo true 바뀌면 ? 비밀글 되야함. 해당 유저와 관리자만 볼 수 있음



  @Builder
  public InquiryRequest(String title, String content,Boolean secretCheckBox) {
    this.title = title;
    this.content = content;
    this.secretCheckBox = secretCheckBox;

  }


  public Inquiry toEntity(String username,String nickName) {
    return Inquiry.builder()
        .title(title)
        .content(content)
        .username(username)
        .nickName(nickName)
        .secretCheckBox(secretCheckBox) //defalt = false, true 이면 비밀글 처리됨
        .build();
  }


}
