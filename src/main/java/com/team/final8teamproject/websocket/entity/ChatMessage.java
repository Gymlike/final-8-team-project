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
public class ChatMessage extends ChatTimestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Long roomId;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String receiver;
    private boolean live = true;

    @OneToMany(mappedBy = "chatMessage", cascade = CascadeType.REMOVE)
    private final Set<ChatRoomMessage> chatRooms = new HashSet<>();
    @Builder
    public ChatMessage(Long roomId, String message, String receiver){
        this.roomId = roomId;
        this.message = message;
        this.receiver = receiver;
    }
    public void setLive(boolean live) {
        this.live = live;
    }
}
