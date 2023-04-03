package com.team.final8teamproject.websocket.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatMessageService {

    private final SimpMessageSendingOperations messageSending;
    @Transactional
    public void sendMessage(){

    }
}