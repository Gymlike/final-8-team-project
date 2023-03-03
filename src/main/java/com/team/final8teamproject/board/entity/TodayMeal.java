package com.team.final8teamproject.board.entity;


import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.share.Timestamped;
import com.team.final8teamproject.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodayMeal extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

//    private String imageUrl;



    private String filepath;


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private BaseEntity user;

    public TodayMeal(String title, String content,String filepath, BaseEntity user) {
        this.title = title;
        this.content = content;
        this.filepath = filepath;
        this.user = user;
    }

    public boolean isWriter(Long userid) {

        return this.user.isUserId(userid);

    }

    public void editSalePost(String title,String content,String filename,String filepath) {
        this.title = title;
        this.content = content;
        this.filepath = filepath;
    }
}