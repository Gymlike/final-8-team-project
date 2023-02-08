package com.team.final8teamproject.contact.comment.entity;
//문의사항에 대한 답글 용


import com.team.final8teamproject.comment.entity.Comment;
import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.user.entity.Timestamped;
import com.team.final8teamproject.user.entity.User;
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
import lombok.Getter;
import lombok.NoArgsConstructor;



//todo 문의사항에 대한 답글 용으로 사용 , contact 패키지 안에서 확장가능성으로 Comment() 로 명명 지음

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class comment extends Timestamped {

  @Getter(AccessLevel.NONE)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(nullable = false)
  private String comment;

  @ManyToOne(fetch = FetchType.LAZY) //todo 유저 조인이 필요할까. username, userId
  @JoinColumn(name = "user_id")
  private User user;

  // 지연로딩은 영속성 컨텍스트에서 조회,이미 조회된 경우는 쿼리 생략함
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "inquiry_id")
  private Inquiry inquiry;

  @Column
  private Long mainInquiryId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name ="parent_id")
  private Comment parent;

  @OneToMany(mappedBy = "parent", orphanRemoval = true)
  private List<Comment> children = new ArrayList<>();



}
// todo  문의사항에 관리자가 답변글 남기면 - > 답변글에  해당 글 주인이 다시 답글 할수 있어야해 .. 음 ..
// todo 유저도 관계가 굳이 필요할까? 필요시 유저 엔티티에 달기
//  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
//    List<Post> postList = new ArrayList<>();
//
//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.REMOVE)
//    List<Comment> commentList = new ArrayList<>();