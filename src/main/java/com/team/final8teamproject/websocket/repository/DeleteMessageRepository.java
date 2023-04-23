package com.team.final8teamproject.websocket.repository;

import com.team.final8teamproject.websocket.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeleteMessageRepository extends JpaRepository<ChatMessage, Long> {
}
