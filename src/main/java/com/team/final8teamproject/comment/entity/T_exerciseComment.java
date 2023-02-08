package com.team.final8teamproject.comment.entity;

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

    public T_exerciseComment(String comment, String username) {
        this.comment = comment;
        this.username = username;
    }
}
