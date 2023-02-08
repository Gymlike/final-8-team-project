package com.team.final8teamproject.contact.dto;

import com.team.final8teamproject.contact.entity.Faq;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class FaqResponse {
 // private final Long id;
  private final String question;
  private final String answer;

  public FaqResponse(Faq faq) {
   // this.id = faq.getId();
    this.question = faq.getQuestion();
    this.answer = faq.getAnswer();
  }
}
