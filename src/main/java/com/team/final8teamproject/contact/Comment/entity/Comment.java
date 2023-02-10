package com.team.final8teamproject.contact.Comment.entity;
//문의사항에 대한 답글 용


import com.team.final8teamproject.contact.entity.Inquiry;
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

//todo 문의사항에 대한 답글 용으로 사용 , contact 패키지 안에서 확장가능성으로 Comment() 로 명명 지음

@Getter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped {

  @Getter(AccessLevel.NONE)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id", nullable = false)
  private Long id;

  @Column(nullable = false)
  private String comment;

  //  @ManyToOne(fetch = FetchType.LAZY) //todo 유저 조인이 필요할까. username, userId
//  @JoinColumn(name = "user_id")
//  private User user;
  @Column(nullable = false)
  private String username;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "inquiry_id")
  private Inquiry inquiry;

//  @Column(nullable = false)
//  private Long parentId;

  @Column(nullable = false)
  private Long mainInquiryId;

  @ManyToOne
  @JoinColumn(name = "parent_id")
  private Comment parent;

  @OneToMany(mappedBy = "parent",orphanRemoval = true)
  private List<Comment> children = new ArrayList<>();


//  public Comment(CommentRequest commentRequest, Inquiry inquiry, String username, Comment parent) {
//        this.comment = commentRequest.getComment();
//        this.inquiry = inquiry;
//        this.username = username;
//        this.parent = parent;
//        this.mainInquiryId =inquiry.getId();

 @Builder
  public Comment(String comment, String username, Inquiry inquiry, Long parentId,
     Long mainInquiryId,
      Comment parent, List<Comment> children) {
    this.comment = comment;
    this.username = username;
    this.inquiry = inquiry;
    this.mainInquiryId = mainInquiryId;
    this.parent = parent;
    this.children = children;
  }



  public void setMainInquiryId(Long id) {
   this.mainInquiryId=id;
  }
}



// todo  문의사항에 관리자가 답변글 남기면 - > 답변글에  해당 글 주인이 다시 답글 할수 있어야해 .. 음 ..
// todo 유저도 관계가 굳이 필요할까? 필요시 유저 엔티티에 달기
//  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
//    List<Post> postList = new ArrayList<>();
//
//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.REMOVE)
//    List<Comment> commentList = new ArrayList<>();