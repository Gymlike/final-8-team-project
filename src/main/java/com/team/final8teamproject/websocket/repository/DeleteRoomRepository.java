package com.team.final8teamproject.websocket.repository;

import com.team.final8teamproject.websocket.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeleteRoomRepository extends JpaRepository<ChatRoom, Long> {
}
