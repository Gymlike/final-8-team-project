package com.team.final8teamproject.contact.entity;

import static org.assertj.core.api.Assertions.*;


import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InquiryTest {

  @Test
  @DisplayName("FAQ 빌더 테스트")
  void builder() {
    //given
    User member1 = User.builder()
        .nickName("member1").email("member1@google.com")
        .phoneNumber("01033334444")
        .password("member1234")
        .username("member1").role(UserRoleEnum.MEMBER).experience(0L)
        .build();

    String title = "title";
    String content = "content";
    boolean secret = false;
    //when
    Inquiry inquiry = Inquiry.builder()
        .username("member1")
        .nickName("member1")
        .title(title)
        .content(content)
        .secret(secret)
        .build();
    //then
    assertThat(inquiry.getId()).isNull();
    assertThat(inquiry.getUsername()).isEqualTo("member1");
    assertThat(inquiry.getNickName()).isEqualTo("member1");
    assertThat(inquiry.getTitle()).isEqualTo("title");
    assertThat(inquiry.getContent()).isEqualTo("content");
    assertThat(inquiry.getSecret()).isFalse();
  }

  @Test
  @DisplayName("Inquiry 업데이트 테스트")
  void update() {
    //given
    Inquiry inquiry = Inquiry.builder()
        .username("member1")
        .nickName("member1")
        .title("title")
        .content("content")
        .secret(false)
        .build();
    //when
    inquiry.update("updateTitle", "updateContent");
    //then
    assertThat(inquiry.getTitle()).isEqualTo("updateTitle");
    assertThat(inquiry.getContent()).isEqualTo("updateContent");
  }

  @Test
  @DisplayName("유효한 사용자 테스트")
  void isWriter() {
    //given
    Inquiry inquiry = Inquiry.builder()
        .username("member1")
        .nickName("member1")
        .title("title")
        .content("content")
        .secret(false)
        .build();
    //when&then
    assertThatNoException().isThrownBy(() -> inquiry.isWriter("member1"));
  }

  @Test
  void isNickName() {
    Inquiry inquiry = Inquiry.builder()
        .username("member1")
        .nickName("member1")
        .title("title")
        .content("content")
        .secret(false)
        .build();
    //when&then
    assertThatNoException().isThrownBy(() -> inquiry.isNickName("member1"));
  }


}