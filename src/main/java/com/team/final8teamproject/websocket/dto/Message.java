package com.team.final8teamproject.websocket.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {

    private String type; // 메시지 타입

    private String sender; // 메시지 보낸사람

    private String receiver; // 메시지

    private Object data;

    public void setSender(String sender) {
        this.sender = sender;
    }
    public void newConnect(){
        this.type = "new";
    }

    public void closeConnect(){
        this.type = "close";
    }

}
