package com.team.final8teamproject.board.like.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class T_exerciseLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardLike_Id")
    private Long id;


    private String username;

    private Long boardId;

    public T_exerciseLike(String username, Long boardId) {
        this.username = username;
        this.boardId = boardId;
    }
}
