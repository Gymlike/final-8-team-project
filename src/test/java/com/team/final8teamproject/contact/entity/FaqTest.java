package com.team.final8teamproject.contact.entity;


import com.team.final8teamproject.contact.dto.UpdateFaqRequest;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


class FaqTest {
  @Test
  @DisplayName("FAQ 빌더 테스트")
  void builder() {
    //given
    String question = "question";
    String answer = "answer";
    Long managerId = 1L;
    //when
    Faq faq = Faq.builder()
        .managerId(managerId)
        .question(question)
        .answer(answer)
        .build();
    //then
    assertThat(faq.getId()).isNull();
    assertThat(faq.getQuestion()).isEqualTo("question");
    assertThat(faq.getAnswer()).isEqualTo("answer");
    assertThat(faq.getManagerId()).isEqualTo(1L);
  }
  @Test
  @DisplayName("FAQ 업데이트 테스트")
  void update() {
    //given

    Faq faq = Faq.builder()
        .managerId(1L)
        .question("question")
        .answer("answer")
        .build();
    //when
    faq.update("updateQuestion", "updateAnswer");
    //then
    assertThat(faq.getQuestion()).isEqualTo("updateQuestion");
    assertThat(faq.getAnswer()).isEqualTo("updateAnswer");
  }

  @Test
  @DisplayName("유효한 사용자 테스트")
  void isWriter() {
    Faq faq = Faq.builder()
        .managerId(1L)
        .question("question")
        .answer("answer")
        .build();
    //when&then
    assertThatNoException().isThrownBy(()->faq.isWriter(1L));
    assertThatThrownBy(()-> faq.isWriter(2L)).isInstanceOf(CustomException.class);
  }

}