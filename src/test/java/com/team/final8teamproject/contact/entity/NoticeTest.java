package com.team.final8teamproject.contact.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.team.final8teamproject.share.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoticeTest {

  @Test
  @DisplayName("Notice 빌더 테스트")
  void builder() {
    //     this.managerId = managerId;
    //        this.title = title;
    //        this.content = content;
    //        this.imageUrl = imageUrl;
    //given
    Long managerId = 1L;
    String title = "title";
    String content = "content";
    String imageUrl = "image";
    //when
    Notice notice = Notice.builder()
        .managerId(managerId)
        .title(title)
        .content(content)
        .imageUrl(imageUrl)
        .build();
    //then
    assertThat(notice.getId()).isNull();
    assertThat(notice.getTitle()).isEqualTo("title");
    assertThat(notice.getContent()).isEqualTo("content");
    assertThat(notice.getImageUrl()).isEqualTo("image");
  }

  @Test
  @DisplayName("Notice 수정 테스트")
  void update() {
    //given
    Long managerId = 1L;
    String title = "title";
    String content = "content";
    String imageUrl = "image";
    //when
    Notice notice = Notice.builder()
        .managerId(managerId)
        .title(title)
        .content(content)
        .imageUrl(imageUrl)
        .build();

    //when
    notice.update("updateTitle", "updateContent", "image");
    //then
    assertThat(notice.getTitle()).isEqualTo("updateTitle");
    assertThat(notice.getContent()).isEqualTo("updateContent");
    assertThat(notice.getImageUrl()).isEqualTo("image");
  }

  @Test
  @DisplayName("유효한 사용자 테스트")
  void isWriter() {
    Notice notice = Notice.builder()
        .managerId(1L)
        .title("title")
        .content("content")
        .build();
    //when&then
    assertThatNoException().isThrownBy(()-> notice.isWriter( 1L)); //예외가 발생하지 않는 경우
    assertThatThrownBy(()->notice.isWriter(2L)).isInstanceOf(CustomException.class);//예외 발생하는 경우

  }

}