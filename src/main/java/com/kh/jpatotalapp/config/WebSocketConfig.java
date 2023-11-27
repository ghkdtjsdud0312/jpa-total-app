package com.kh.jpatotalapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

// "ws/chat" 엔드포인트에 대한 WebSocket 연결이 활성화되고, WebSocketHandler가 해당 연결을 처리
// @EnableWebSocket 어노테이션에 의해 WebSocket 지원이 활성화되어 있음

@Configuration // Spring에게 이 클래스를 구성 클래스로 사용하라고 알려줌(클래스 생성)
@RequiredArgsConstructor // 클래스의 final 필드에 대한 생성자를 자동으로 생성하는데 여기서는 WebSocketHandler를 생성자의 매개변수로 받아들임
@EnableWebSocket // WebSocket 지원을 활성화하는 어노테이션이고, 이 어노테이션이 사용된 클래스는 WebSocket 관련 구성을 수행(어노테이션 설정)
public class WebSocketConfig implements WebSocketConfigurer { // WebSocketConfigurer : 웹소켓을 사용하려면 클라이언트가 보내오는 통신을 처리할 handler가 필요
    private final WebSocketHandler webSocketHandler;  // WebSocketHandler를 주입받는 생성자 필드이고, WebSocket 연결을 관리하고 처리

    @Override
    // registerWebSocketHandlers : 웹소켓을 사용하려면 클라이언트가 보내오는 통신을 처리할 handler가 필요
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) { // webSocketHandler를 "ws/chat" 엔드포인트에 등록하고, 모든 출처에서의 연결을 허용하도록 설정되어 잇음(setAllowedOrigins("*")).
        registry.addHandler(webSocketHandler, "ws/chat").setAllowedOrigins("*"); // "ws/chat" 채팅방 경로 지나감
        // addHandler : SocketTextHandler()는 웹소켓 핸들러, "ws/chat" : 웹소켓 연결 주소
        // setAllowedOrigins("*") : CORS정책을 위반하지 않게 해줌
    }
}