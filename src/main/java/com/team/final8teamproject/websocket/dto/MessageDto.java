package com.team.final8teamproject.websocket.dto;
import com.team.final8teamproject.websocket.entity.MessageType;
import lombok.*;

@Getter
@NoArgsConstructor
public class MessageDto {
    private String sender; // 메시지 보낸사람
    private Long roomId; //보내는 방
    private String message;// 메시지
    private MessageType messageType; // 메시지 타입

    @Builder
    public MessageDto(String sender, Long roomId, String message,
                      MessageType messageType){
        this.sender = sender;
        this.roomId = roomId;
        this.message= message;
        this.messageType = messageType;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}