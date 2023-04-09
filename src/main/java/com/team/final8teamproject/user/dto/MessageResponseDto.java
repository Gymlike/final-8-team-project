package com.team.final8teamproject.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageResponseDto implements Serializable {

    private String message;

    public MessageResponseDto(String message){
        this.message = message;
    }

}
