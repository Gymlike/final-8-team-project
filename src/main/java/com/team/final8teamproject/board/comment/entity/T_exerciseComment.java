package com.team.final8teamproject.board.comment.entity;

import com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply;

import com.team.final8teamproject.share.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class T_exerciseComment extends Timestamped implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String username;

    private Long boardId;

    private String userNickname;

    public T_exerciseComment(String comment, String username,Long boardId,String nickname) {
        this.comment = comment;
        this.username = username;
        this.boardId =boardId;
        this.userNickname = nickname;
    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comments" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<T_exerciseCommentReply> commentReplyList = new ArrayList<>();

    public boolean isWriter(String username) {
        return this.username.equals(username);
    }

    public void update(String commentContent) {
        this.comment = commentContent;
    }
}
