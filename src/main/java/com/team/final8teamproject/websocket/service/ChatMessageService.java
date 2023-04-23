package com.team.final8teamproject.websocket.service;

import com.team.final8teamproject.base.repository.BaseRepository;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import com.team.final8teamproject.websocket.dto.MessageDto;
import com.team.final8teamproject.websocket.entity.ChatMessage;
import com.team.final8teamproject.websocket.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final BaseRepository baseRepository;

    @Transactional
    public void saveMessage(MessageDto messageDto) {
        ChatMessage chatMessage = ChatMessage.builder()
                .roomId(messageDto.getRoomId())
                .receiver(messageDto.getSender())
                .message(messageDto.getMessage())
                .build();
        chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getRoomMessages(Long roomId, String username) {
        if(baseRepository.existsByUsername(username)) {
            return chatMessageRepository.findByRoomIdOrderByCreatedDateTimeDesc(roomId);
        }else{
            throw new CustomException(ExceptionStatus.ACCESS_DENINED);
        }
    }
}
