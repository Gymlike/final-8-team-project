package com.team.final8teamproject.contact.dto;

import com.team.final8teamproject.contact.entity.Faq;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class FaqRequest {


  @NotBlank(message = "This is not blank")
  private final String question;
  @NotBlank(message = "This is not blank")
  private final String answer;


  public FaqRequest(String question, String answer) {
    this.question = question;
    this.answer = answer;
  }

  public Faq toEntity(Long managerId){
  return Faq.builder()
      .managerId(managerId)
      .question(question)
      .answer(answer)
      .build();
  }
}
