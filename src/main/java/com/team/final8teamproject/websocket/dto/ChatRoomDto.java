package com.team.final8teamproject.websocket.dto;


import com.team.final8teamproject.websocket.entity.ChatRoom;
import lombok.Getter;
@Getter
public class ChatRoomDto {
    private Long id;
    private String roomName;
    private String nickNameA;
    private String nickNameB;

    public ChatRoomDto(ChatRoom chatRoom) {
        this.id = chatRoom.getId();
        this.roomName = chatRoom.getRoomTitle();
        this.nickNameA = chatRoom.getOwner().getNickName();
        this.nickNameB = chatRoom.getUser().getNickName();
    }
}