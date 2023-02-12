package com.team.final8teamproject.board.comment.commentReply.entity;

import com.team.final8teamproject.board.comment.entity.T_exerciseComment;
import com.team.final8teamproject.board.comment.entity.TodayMealComment;
import com.team.final8teamproject.share.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodayMealCommentReply extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_REPLY_ID")
    private Long id;

    @Column(nullable = false)
    private String commentContent;

    @Column(nullable = false)
    private String username;


    @ManyToOne
    @JoinColumn(name ="COMMENT_ID")
    private TodayMealComment comments;

    public TodayMealCommentReply(String commentContent, String username, TodayMealComment comments) {
        this.commentContent = commentContent;
        this.username = username;
        this.comments = comments;
        comments.getCommentReplyList().add(this);
    }

    public boolean isWriter(String username) {
        return this.username.equals(username);
    }

    public void update(String commentReplyContent) {
        this.commentContent = commentReplyContent;
    }

}
