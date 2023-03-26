package com.team.final8teamproject.websocket.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

//persistence
@Entity
//lombok
@NoArgsConstructor
@Getter
public class Message extends ChatTimestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;
    private String receiver;
    private Long roomId;
    private boolean live = true;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    private final Set<ChatRoomMessage> chatRooms = new HashSet<>();
    @Builder
    public Message(Long roomId,String message, String receiver){
        this.roomId = roomId;
        this.message = message;
        this.receiver = receiver;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
