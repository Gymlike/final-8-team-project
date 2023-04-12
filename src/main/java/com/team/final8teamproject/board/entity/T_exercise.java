package com.team.final8teamproject.board.entity;



import com.team.final8teamproject.share.Timestamped;
import com.team.final8teamproject.base.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/*

title이 content에 비해서 수정이 적을 가능성이 크지만
많을수도 있다.

1.
하지만 title이용 한 검색이 더많을것이고
이것이 빨리 처리되는것이 DB 부하가 적고 응답시간이 빠를것이기때문에
인덱스 조각화가 어느정도 발생하는것을 감안하고 대처방안을 만들며
title에 인덱스를 적용시켜서 성능을 비교하였을때 더 좋았습니다.

2.
해보니 성능이 더 좋아졌고 Jmeter도 활용하여 부하테스틑 해봤을때
인덱스를 적용한것이 더 좋았습니다.

 */
public class T_exercise extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String imageUrl;


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private BaseEntity user;

    public T_exercise(String title, String content, String imageUrl,BaseEntity user) {
        this.title = title;
        this.content = content;
        this.imageUrl=imageUrl;
        this.user = user;
    }

    public boolean isWriter(Long userid) {

        return this.user.isUserId(userid);

    }

    public void editSalePost(String title,String content,String imageUrl) {
        this.title = title;
        this.content = content;
        this.imageUrl= imageUrl;
    }

    public Long returnPostId(){
        return id;
    }

}