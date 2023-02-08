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


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;


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

