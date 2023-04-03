package com.team.final8teamproject.websocket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class ChatRoom extends ChatTimestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomTitle;
    private String ownerNickName;
    private String userNickName;
    private boolean live = true;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private final Set<ChatRoomMessage> messages = new HashSet<>();
    @Builder
    public ChatRoom(String roomTitle,
                    String ownerNickName,
                    String userNickName) {
        this.roomTitle = roomTitle;
        this.ownerNickName = ownerNickName;
        this.userNickName = userNickName;

    }

    public void setLive(boolean live) {
        this.live = live;
    }
}