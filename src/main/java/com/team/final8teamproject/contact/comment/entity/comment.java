package com.team.final8teamproject.contact.comment.entity;
//문의사항에 대한 답글 용

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

//todo 문의사항에 대한 답글 용으로 사용 , contact 패키지 안에서 확장가능성으로 Comment() 로 명명 지음
// todo 관리자만 답글 가능
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class comment {

  @Getter(AccessLevel.NONE)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private Long inquiryId;
  private String username;
  private String comment;

}
