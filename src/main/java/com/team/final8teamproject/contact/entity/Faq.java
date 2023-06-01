package com.team.final8teamproject.contact.entity;


import com.team.final8teamproject.share.Timestamped;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
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
  @Column(name = "faq_id", nullable = false)
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

  public void isWriter(Long managerId) {
    if(!this.managerId.equals(managerId)){
      throw new CustomException(ExceptionStatus.WRONG_USER_T0_CONTACT);
    }
  }
}
