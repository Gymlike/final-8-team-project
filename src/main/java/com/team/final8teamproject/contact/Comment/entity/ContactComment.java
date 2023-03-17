package com.team.final8teamproject.contact.Comment.entity;

import com.team.final8teamproject.share.Timestamped;
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
import lombok.Setter;

/**inquiry , user 연관관계  x  없이 구현
 */


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ContactComment extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  @Column(nullable = false)
  private String comments;

  @Column(nullable = false)
  private String username;


  @Column(nullable = false)
  private Long inquiryId;
  @Column(nullable = false)
  private String nickName;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private ContactComment parent;

  @OneToMany(mappedBy = "parent", orphanRemoval = true)
  private List<ContactComment> children = new ArrayList<>();

  private int depth;
  @Builder
  public ContactComment(String comments, String username, Long inquiryId, String nickName,
      ContactComment parent, int depth) {
    this.comments = comments;
    this.username = username;
    this.inquiryId = inquiryId;
    this.nickName = nickName;
    this.parent = parent;
    this.depth = depth;
  }


  public void update(String comments) {
    this.comments = comments;
  }

  public boolean isWriter(String username) {
    return this.username.equals(username);
  }

  public void setParent(ContactComment parent) {
    this.parent = parent;
  }

  public void setDepth(int depth) {
    this.depth = depth;
  }

  public boolean isInquiryId(Long inquiryId) {
    return this.inquiryId.equals(inquiryId);
  }
}



