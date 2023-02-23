package com.team.final8teamproject.websocket.config;
import com.team.final8teamproject.websocket.handler.ChatHandler;
import com.team.final8teamproject.websocket.handler.StompHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final StompHandler stompHandler;
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//// 이 경로를 Subscribe 하고 있는 클라이언트에게 메세지를 전달하는 작업을 수행하게 됨
//        config.enableSimpleBroker("/sub");
//        // 클라이언트에서 요청을 전송할 경로 (이 경로 뒤에 컨트롤러의 매핑 주소가 붙음)
//        config.setApplicationDestinationPrefixes("/pub");
//    }
    private final ChatHandler chatHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler, "ws/chat").setAllowedOrigins("*");
    }
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws/chat")
//                .setAllowedOrigins("*")
//                .withSockJS();
//    }

//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(stompHandler); // 핸들러 등록
//    }


}