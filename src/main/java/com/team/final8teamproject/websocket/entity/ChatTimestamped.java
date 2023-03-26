package com.team.final8teamproject.websocket.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Getter             //get 함수를 자동으로 생성한다.
@MappedSuperclass   //멤버 변수가 컬럼이 되도록합니다.
public class ChatTimestamped  implements Serializable {
    //보여 주기 위한 작성시간
    @CreatedDate
    private String createdDate;

    //보내줄때 기준점이 되는 작성시간
    private Long createdDateTime;

    //이거 이렇게 해도 되려나.. 변환하면서 시간좀 걸릴탠데
    @PrePersist
    public void onPrePersist(){
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        //사용할 zone 아이디 값 입니다.
        ZoneId zoneid = ZoneId.of("Asia/Seoul");
        //#1. 현재 시간의 값 밀리세컨드 변환
        this.createdDateTime = LocalDateTime.now().atZone(zoneid).toInstant().toEpochMilli();
    }


}