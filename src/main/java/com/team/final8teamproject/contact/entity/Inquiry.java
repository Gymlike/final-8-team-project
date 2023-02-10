package com.team.final8teamproject.contact.entity;


//import com.team.final8teamproject.contact.contactComment.entity.ContactComment;
import com.team.final8teamproject.user.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Inquiry extends Timestamped {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;


  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  private Boolean secretCheckBox; // todo ** 관리자만 보기 와 모두 보기 선택지 - 관리자만 보기시 관리지만 볼 수 있음

  @Builder
  public Inquiry(String username, String title, String content, Boolean secretCheckBox) {
    this.username = username;
    this.title = title;
    this.content = content;
    this.secretCheckBox = secretCheckBox;
  }


  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
}

