package com.team.final8teamproject.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageResponseDto {

    private String message;

    public MessageResponseDto(String message){
        this.message = message;
    }

}
