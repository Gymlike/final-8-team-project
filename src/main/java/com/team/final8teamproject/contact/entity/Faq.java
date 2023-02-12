package com.team.final8teamproject.contact.entity;

import com.team.final8teamproject.user.entity.Timestamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

  public void update(String question, String answer) {
    this.question = question;
    this.answer = answer;
  }
}
