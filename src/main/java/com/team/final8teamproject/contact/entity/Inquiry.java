package com.team.final8teamproject.contact.entity;

import com.team.final8teamproject.contact.dto.InquiryRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Inquiry {

//문의하기
  //1. 회원(유저), 사업자 - 문의글 등록 / 수정 /삭제
  //2. 관리자 - 문의 답변 등록/ 수정 /삭제 / 고객문의글 삭제 기능

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
// 문의 답변 :  포스트에 댓글 달기와 유사 // todo  댓글 클래스 별도로 구현 필요한가/??

  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  private Boolean secretCheckBox; // todo ** 관리자만 보기 와 모두 보기 선택지 - 관리자만 보기시 관리지만 볼 수 있음

  @Builder
  public Inquiry(Long userId, String title, String content, Boolean secretCheckBox) {
    this.userId = userId;
    this.title = title;
    this.content = content;
    this.secretCheckBox = secretCheckBox;
  }

  public void update(InquiryRequest inquiryRequest) {
    this.title = title;
    this.content = content;
  }
}

