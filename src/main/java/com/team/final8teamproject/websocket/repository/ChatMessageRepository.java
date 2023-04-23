package com.team.final8teamproject.websocket.repository;

import com.team.final8teamproject.websocket.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByRoomId(Long roomId);

    List<ChatMessage> findByRoomIdOrderByCreatedDateTimeDesc(Long roomId);
}
