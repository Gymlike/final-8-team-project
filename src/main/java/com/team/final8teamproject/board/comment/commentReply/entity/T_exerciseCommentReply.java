package com.team.final8teamproject.board.comment.commentReply.entity;

import com.team.final8teamproject.share.TimeStamp;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class T_exerciseCommentReply extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_REPLY_ID")
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String username;


    private Long commentId;

    public T_exerciseCommentReply(String comment, String username, Long commentId) {
        this.comment = comment;
        this.username = username;
        this.commentId = commentId;
    }
}
