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
// WebSocketHandler를 상속 받아서 WebSocketHandler를 구현
public class WebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper; //Json 문자열로 변환하기 위한 객체
    private final ChatService chatService; // 채팅방 관련 비지니스 로직을 처리할 서비스
    private final Map<WebSocketSession, String> sessionRoomIdMap = new ConcurrentHashMap<>(); // 세션과 채팅방 ID를 매핑할 맵
    @Override
    // 클라이언트가 서버로 연결을 시도할 때 호출되는 메서드
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload(); // 클라이언트가 전송한 메세지
        log.warn("{}", payload);
        // Json 문자열을 ChatMessageDto 객체로 반환
        ChatMessageDto chatMessage = objectMapper.readValue(payload, ChatMessageDto.class); // 제이슨을 객체로 매핑해주고 ChatMessageDto로 받음
        String roomId = chatMessage.getRoomId(); // 룸아이디와 채팅방 아이디 세션을 매핑해, 채팅방 ID
        // 세션아이디와 채팅방 ID를 매핑
        sessionRoomIdMap.put(session, chatMessage.getRoomId()); // 세션아이디와 채팅방 ID를 매핑
        if(chatMessage.getType() == ChatMessageDto.MessageType.ENTER) { // 메세지 타입이 ENTER이면
            chatService.addSessionAndHandleEnter(roomId, session, chatMessage); // 채팅방에 입장한 세션 추가
        } else if (chatMessage.getType() == ChatMessageDto.MessageType.CLOSE){
            chatService.removeSessionAndHandleExit(roomId, session, chatMessage);
        } else {
            chatService.sendMessageToAll(roomId, chatMessage);
        }
    }
    @Override
    // WebSocket 연결의 생명주기를 관리하는데 있어 중요한 부분을 담당, 연결 종료 직후 호출
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션과 매핑된 채팅방 ID 가져오기
        String roomId = sessionRoomIdMap.remove(session);
        if (roomId != null) {
         ChatMessageDto chatMessage = new ChatMessageDto();
         chatMessage.setType(ChatMessageDto.MessageType.CLOSE);
         chatService.removeSessionAndHandleExit(roomId, session, chatMessage);
        }
    }
}