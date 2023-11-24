package com.kh.jpatotalapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.jpatotalapp.dto.ChatMessageDto;
import com.kh.jpatotalapp.dto.ChatRoomResDto;
import com.kh.jpatotalapp.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;
    private final Map<WebSocketSession, String> sessionRoomIdMap = new ConcurrentHashMap<>();
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.warn("{}", payload);
        ChatMessageDto chatMessage = objectMapper.readValue(payload, ChatMessageDto.class);
        ChatRoomResDto chatRoom = chatService.findRoomById(chatMessage.getRoomId());
        // 세션과 채팅방 ID를 매핑
        sessionRoomIdMap.put(session, chatMessage.getRoomId());
        chatRoom.handlerActions(session, chatMessage, chatService);
    }
    @Override
    // WebSocket 연결의 생명주기를 관리하는데 있어 중요한 부분을 담당, 연결 종료 직 후 호출
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션과 매핑된 채팅방 ID 가져오기
        String roomId = sessionRoomIdMap.remove(session);
        if (roomId != null) {
            ChatRoomResDto chatRoom = chatService.findRoomById(roomId);
            chatRoom.handleSessionClosed(session, chatService);
        }
    }
}