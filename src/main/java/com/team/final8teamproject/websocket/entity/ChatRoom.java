package com.team.final8teamproject.websocket.entity;

import com.team.final8teamproject.base.entity.BaseEntity;
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

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private BaseEntity owner;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private BaseEntity user;
    private boolean live = true;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
    private final Set<ChatRoomMessage> messages = new HashSet<>();
    @Builder
    public ChatRoom(BaseEntity owner,
                    BaseEntity user) {
        this.roomTitle = owner.getNickName()+"-"+user.getNickName();
        this.owner = owner;
        this.user = user;
    }



    public void setLive(boolean live) {
        this.live = live;
    }
}