
package com.team.final8teamproject.share;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter             //get함수를 자동으로 생성한다.
@MappedSuperclass   //멤버 변수가 컬럼이 되도록합니다.
@EntityListeners(AuditingEntityListener.class)//변경 되었을때 자동으로 기록합니다.
public class Timestamped {

    @CreatedDate//최소 생성시점
    private LocalDateTime createdDate;


    private String createdDateString;
    @LastModifiedDate//마지막 변경시점
    private LocalDateTime modifiedDate;


    @PrePersist
    public void onPrePersit(){
        this.createdDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }


}