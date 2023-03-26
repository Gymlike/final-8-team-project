package com.team.final8teamproject.websocket.controller;

import com.team.final8teamproject.websocket.dto.MessageDto;
import com.team.final8teamproject.websocket.entity.MessageType;
import com.team.final8teamproject.websocket.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatMessageController {
    private final SimpMessageSendingOperations sendingOperations;

    private final ChatMessageService chatMessageService;
    //서비스를 만들어서 나누는게 좋을까..
    //다 보내야하는 것을 보내지 않고 여기서 처리하는게 좋을까
    //확장을 할수도있으니 빼버려?
    //매번 확인하면 매번 2가 곱해지는데 괜찮은건가
    @MessageMapping("/send/attend")
    public void attendMessage(@Payload MessageDto messageDto, SimpMessageHeaderAccessor headerAccessor){
        messageDto.setMessage(messageDto.getSender() + "님이 입장하셨습니다.");

        //반환 결과를 socket session에 user로 저장
        headerAccessor.getSessionAttributes().put("user", messageDto.getMessage());
        headerAccessor.getSessionAttributes().put("roomId", messageDto.getRoomId());

        sendingOperations.convertAndSend("/sub/send" + messageDto.getRoomId(), messageDto);
    }

    @MessageMapping("/send/checkout")
    public void checkOutMessage(@Payload MessageDto messageDto, SessionDisconnectEvent event){
        messageDto.setMessage(messageDto.getSender() + "님이 퇴장하셨습니다..");
        sendingOperations.convertAndSend("/sub/send" + messageDto.getRoomId(), messageDto);
    }

    @MessageMapping("/send/message")
    public void sendMessage(MessageDto messageDto){
        sendingOperations.convertAndSend("/sub/send" + messageDto.getRoomId(), messageDto);

    }
}
