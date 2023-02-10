package com.team.final8teamproject.contact.Comment.entity;
//문의사항에 대한 답글 용


import com.team.final8teamproject.user.entity.Timestamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(nullable = false)
  private String comments;

  @Column(nullable = false)
  private String username;



  //inquiry , user 연관관계  x  없이 구현
  private Long inquiryId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private Comment parent;

  @OneToMany(mappedBy = "parent",orphanRemoval = true)
  private List<Comment> children = new ArrayList<>();

  @Builder
  public Comment(String comments, String username, Long inquiryId, Comment parent) {
    this.comments = comments;
    this.username = username;
    this.inquiryId = inquiryId;
    this.parent = parent;
  }

  public void update(String comments) {
    this.comments = comments;
  }

}



// todo  문의사항에 관리자가 답변글 남기면 - > 답변글에  해당 글 주인이 다시 답글 할수 있어야해 .. 음 ..

