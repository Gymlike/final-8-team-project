package com.team.final8teamproject.websocket.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker // 웹소켓 stome사용시필요 안쓰는데 사용하면 오류남
@EnableWebSocket
@Slf4j
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint( "ws/chat").setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
// 이 경로를 Subscribe 하고 있는 클라이언트에게 메세지를 전달하는 작업을 수행하게 됨
        config.enableSimpleBroker("/sub");
        // 클라이언트에서 요청을 전송할 경로 (이 경로 뒤에 컨트롤러의 매핑 주소가 붙음)
        config.setApplicationDestinationPrefixes("/pub");
    }
}