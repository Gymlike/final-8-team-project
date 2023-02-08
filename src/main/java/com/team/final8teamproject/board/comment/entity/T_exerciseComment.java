package com.team.final8teamproject.board.comment.entity;

import com.team.final8teamproject.share.TimeStamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class T_exerciseComment extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String username;

    private Long boardId;

    public T_exerciseComment(String comment, String username,Long boardId) {
        this.comment = comment;
        this.username = username;
        this.boardId =boardId;
    }

    public boolean isWriter(String username) {
        return this.username.equals(username);
    }

    public void update(String commentContent) {
        this.comment = commentContent;
    }
}
