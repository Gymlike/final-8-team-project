package com.team.final8teamproject.board.entity;


import com.team.final8teamproject.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class T_exercise extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

//    private String imageUrl;

    private String filename;

    private String filepath;


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private BaseEntity user;

    public T_exercise(String title, String content, String filename, String filepath,BaseEntity user) {
        this.title = title;
        this.content = content;
        this.filename = filename;
        this.filepath = filepath;
        this.user = user;
    }

    public boolean isWriter(Long userid) {

        return this.user.isUserId(userid);

    }

    public void editSalePost(String title,String content,String filename,String filepath) {
        this.title = title;
        this.content = content;
        this.filename = filename;
        this.filepath = filepath;
    }
}