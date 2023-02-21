package com.team.final8teamproject.board.like.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodayMealLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardLike_Id")
    private Long id;


    private String username;

    private Long boardId;

    public TodayMealLike(String username, Long boardId) {
        this.username = username;
        this.boardId = boardId;
    }
}
