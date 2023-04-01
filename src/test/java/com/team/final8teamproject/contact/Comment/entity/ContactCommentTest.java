package com.team.final8teamproject.contact.Comment.entity;

import static org.assertj.core.api.Assertions.*;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContactCommentTest {
  @Test
  @DisplayName("ContactComment 빌더 테스트")
  void builder() {
    //given
    ContactComment parent = new ContactComment();
    //when
    ContactComment contactComment = ContactComment.builder()
        .comments("댓글")
        .username("username")
        .inquiryId(1L)
        .nickName("nickname")
        .parent(parent)
        .depth(0)
        .build();
    //then
    assertThat(contactComment.getId()).isNull();
    assertThat(contactComment.getUsername()).isEqualTo("username");
    assertThat(contactComment.getComments()).isEqualTo("댓글");
    assertThat(contactComment.getInquiryId()).isEqualTo(1L);
    assertThat(contactComment.getNickName()).isEqualTo("nickname");
    assertThat(contactComment.getParent()).isEqualTo(parent);
    assertThat(contactComment.getDepth()).isEqualTo(0);
  }
  @Test
  @DisplayName("ContactComment 업데이트 테스트")
  void update() {
    //given
    ContactComment parent = new ContactComment();
    ContactComment contactComment = ContactComment.builder()
        .comments("댓글")
        .username("username")
        .inquiryId(1L)
        .nickName("nickname")
        .parent(parent)
        .depth(0)
        .build();
    //when
    contactComment.update("updateComments");
    //then
    assertThat(contactComment.getComments()).isEqualTo("updateComments");
  }

  @Test
  @DisplayName("유효한 사용자 테스트")
  void isWriter() {
    //given
    ContactComment parent = new ContactComment();
    ContactComment contactComment = ContactComment.builder()
        .comments("댓글")
        .username("username")
        .inquiryId(1L)
        .nickName("nickname")
        .parent(parent)
        .depth(0)
        .build();
    //when&then
    assertThatNoException().isThrownBy(()->contactComment.isWriter("username"));
  }

  @Test
  @DisplayName("부모댓글 저장 테스트")
  void setParent() {
    //given
    ContactComment parent = new ContactComment();
    ContactComment parent1 = new ContactComment();

    ContactComment contactComment = ContactComment.builder()
        .comments("댓글")
        .username("username")
        .inquiryId(1L)
        .nickName("nickname")
        .parent(parent)
        .depth(0)
        .build();
    //when
    contactComment.setParent(parent1);
    //then
    assertThat(contactComment.getParent()).isEqualTo(parent1);
  }

  @Test
  @DisplayName("댓글 계층 테스트")
  void setDepth() {
    //given
    ContactComment parent = new ContactComment();
    ContactComment contactComment = ContactComment.builder()
        .comments("댓글")
        .username("username")
        .inquiryId(1L)
        .nickName("nickname")
        .parent(parent)
        .depth(0)
        .build();
    int depth = 1;
    //when
    contactComment.setDepth(depth);
    //then
    assertThat(contactComment.getDepth()).isEqualTo(depth);

  }

  @Test
  @DisplayName("유효한 inquiry 인지  테스트")
  void isInquiryId() {
    //given
    ContactComment parent = new ContactComment();
    ContactComment contactComment = ContactComment.builder()
        .comments("댓글")
        .username("username")
        .inquiryId(1L)
        .nickName("nickname")
        .parent(parent)
        .depth(0)
        .build();
    //when&then
    assertThatNoException().isThrownBy(()-> contactComment.isInquiryId(1L));
  }


}