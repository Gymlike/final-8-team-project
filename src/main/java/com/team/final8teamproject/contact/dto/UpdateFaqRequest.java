package com.team.final8teamproject.contact.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)

public class UpdateFaqRequest {
  private final String question;
  private final String answer;

  public UpdateFaqRequest(String question, String answer) {
    this.question = question;
    this.answer = answer;
  }
}
