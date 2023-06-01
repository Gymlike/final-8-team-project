package com.team.final8teamproject.contact.Comment.entity;

import static com.team.final8teamproject.contact.entity.QInquiry.inquiry;
import static org.assertj.core.api.Assertions.*;


import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.share.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * assertThatNoException().isThrownBy(()-> contactComment.isInquiryId(1L));//예외가 없을때
 * assertThatThrownBy(()->contactComment.isInquiryId(2L)).isInstanceOf(CustomException.class); //예외 발생시
 */
@ExtendWith(MockitoExtension.class)
class ContactCommentTest {
  @Test
  @DisplayName("ContactComment 빌더 테스트")
  void builder() {
    //given
    ContactComment parent = new ContactComment();
    Inquiry inquiry = new Inquiry("username","nick","title","content",false);
    //when
    ContactComment contactComment = ContactComment.builder()
        .comments("댓글")
        .username("username")
        .inquiry(inquiry)
        .nickName("nickname")
        .parent(parent)
        .depth(0)
        .build();
    //then
    assertThat(contactComment.getId()).isNull();
    assertThat(contactComment.getUsername()).isEqualTo("username");
    assertThat(contactComment.getComments()).isEqualTo("댓글");
    assertThat(contactComment.getInquiry().getTitle()).isEqualTo("title");
    assertThat(contactComment.getNickName()).isEqualTo("nickname");
    assertThat(contactComment.getParent()).isEqualTo(parent);
    assertThat(contactComment.getDepth()).isEqualTo(0);
  }
  @Test
  @DisplayName("ContactComment 업데이트 테스트")
  void update() {
    //given
    ContactComment parent = new ContactComment();
    Inquiry inquiry = new Inquiry("username","nick","title","content",false);
    ContactComment contactComment = ContactComment.builder()
        .comments("댓글")
        .username("username")
        .inquiry(inquiry)
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
    Inquiry inquiry = new Inquiry("username","nick","title","content",false);
    ContactComment contactComment = ContactComment.builder()
        .comments("댓글")
        .username("username")
        .inquiry(inquiry)
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
    Inquiry inquiry = new Inquiry("username","nick","title","content",false);

    ContactComment contactComment = ContactComment.builder()
        .comments("댓글")
        .username("username")
        .inquiry(inquiry)
        .nickName("nickname")
        .parent(parent)
        .depth(0)
        .build();
    //when
    contactComment.setParent(parent1);
    //then
    assertThat(contactComment.getParent()).isEqualTo(parent1);
  }
/////////
  @Test
  @DisplayName("유효한 inquiry 인지  테스트")
  void isInquiry() {
    //given
    ContactComment parent = new ContactComment();
    Inquiry inquiry = new Inquiry("username","nick","title","content",false);
    Inquiry inquiry1 = new Inquiry("username","nick","title","content",false);
    ContactComment contactComment = ContactComment.builder()
        .comments("댓글")
        .username("username")
        .inquiry(inquiry)
        .nickName("nickname")
        .parent(parent)
        .depth(0)
        .build();
    //when&then
    assertThatNoException().isThrownBy(()-> contactComment.isInquiry(inquiry));//예외가 없을때
    assertThatThrownBy(()->contactComment.isInquiry(inquiry1)).isInstanceOf(CustomException.class); //예외 발생시
  }


}