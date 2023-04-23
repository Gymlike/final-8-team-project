package com.team.final8teamproject.websocket;

import com.team.final8teamproject.security.jwt.JwtUtil;
import com.team.final8teamproject.websocket.dto.RequestChatConnecting;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.HashMap;

@RequiredArgsConstructor
@Component
public class StompInterceptor implements ChannelInterceptor {
    private final JwtUtil jwtTokenProvider;

    public static final Map<String, RequestChatConnecting> connectedUserPool = new HashMap<>();
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        var seesionId = accessor.getSessionId();
        if(accessor.getCommand() == StompCommand.CONNECT) {
            if(!jwtTokenProvider.validateToken(accessor.getFirstNativeHeader("Authorization")))
                throw new AccessDeniedException("");
        }
        return message;
    }
}