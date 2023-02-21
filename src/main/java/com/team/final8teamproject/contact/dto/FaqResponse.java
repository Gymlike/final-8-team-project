package com.team.final8teamproject.contact.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.final8teamproject.contact.entity.Faq;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class FaqResponse {
  private final Long id;
  private final String question;
  private final String answer;
  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")

  private final LocalDateTime createdDate;
  //private final LocalDateTime modifiedDate;

  public FaqResponse(Faq faq) {
    this.id = faq.getId();
    this.question = faq.getQuestion();
    this.answer = faq.getAnswer();
    this.createdDate = faq.getCreatedDate();
   // this.modifiedDate = faq.getModifiedDate();
  }
}
