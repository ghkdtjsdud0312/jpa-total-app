//package com.kh.jpatotalapp.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.kh.jpatotalapp.dto.ChatMessageDto;
//import com.kh.jpatotalapp.dto.ChatRoomResDto;
//import com.kh.jpatotalapp.service.ChatService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//@RequiredArgsConstructor
//@Slf4j
//@Component
//public class WebSocketHandler extends TextWebSocketHandler {
//
//    private final ObjectMapper objectMapper;
//    private final ChatService chatService;
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();
//        log.warn("{}", payload);
//        ChatMessageDto chatMessage = objectMapper.readValue(payload, ChatMessageDto.class);
//        ChatRoomResDto chatRoom = chatService.findRoomById(chatMessage.getRoomId());
//        chatRoom.handlerActions(session, chatMessage, chatService);
//    }
//
//}