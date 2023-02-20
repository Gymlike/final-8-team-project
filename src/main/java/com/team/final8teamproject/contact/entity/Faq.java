package com.team.final8teamproject.contact.entity;

import com.team.final8teamproject.contact.dto.UpdateFaqRequest;
import com.team.final8teamproject.share.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Faq extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(nullable = false)
  private Long managerId;

  @Column(nullable = false)
  private String question;

  @Column(nullable = false)
  private String answer;

  @Builder
  public Faq(Long managerId, String question, String answer) {
    this.managerId = managerId;
    this.question = question;
    this.answer = answer;
  }

  public void update(UpdateFaqRequest updateFaqRequest) {
   this.question = updateFaqRequest.getQuestion();
   this.answer = updateFaqRequest.getAnswer();
  }
}
