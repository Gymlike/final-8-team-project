package com.team.final8teamproject.contact.entity;


import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class FaqTest {

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
}