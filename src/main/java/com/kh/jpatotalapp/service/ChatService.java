//package com.kh.jpatotalapp.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.kh.jpatotalapp.dto.ChatRoomResDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.*;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class ChatService {
//    private final ObjectMapper objectMapper;
//    private Map<String, ChatRoomResDto> chatRooms;
//
//    @PostConstruct // 의존성 주입 이후 초기화를 수행하는 메소드
//    private void init() {
//        chatRooms = new LinkedHashMap<>();
//    }
//    public List<ChatRoomResDto> findAllRoom() {
//        return new ArrayList<>(chatRooms.values());
//    }
//    public ChatRoomResDto findRoomById(String roomId) {
//        return chatRooms.get(roomId);
//    }
//
//    // 방 개설하기
//    public ChatRoomResDto createRoom(String name) {
//        String randomId = UUID.randomUUID().toString();
//        log.info("UUID : " + randomId);
//        ChatRoomResDto chatRoom = ChatRoomResDto.builder()
//                .roomId(randomId)
//                .name(name)
//                .regDate(LocalDateTime.now())
//                .build();
//        chatRooms.put(randomId, chatRoom);
//        return chatRoom;
//    }
//    public <T> void sendMessage(WebSocketSession session, T message) {
//        try {
//            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
//        } catch(IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//}