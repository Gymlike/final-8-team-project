package com.team.final8teamproject.websocket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class ChatRoomMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;

    public ChatRoomMessage(ChatRoom chatRoom, Message message) {
        this.chatRoom = chatRoom;
        this.message = message;
    }
}