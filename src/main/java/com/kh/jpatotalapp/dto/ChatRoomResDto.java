//package com.kh.jpatotalapp.dto;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.kh.jpatotalapp.service.ChatService;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.socket.WebSocketSession;
//
//import java.time.LocalDateTime;
//import java.util.Collections;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Getter
//@Slf4j
//public class ChatRoomResDto {
//    private String roomId;
//    private String name;
//    private LocalDateTime regDate;
//
//    @JsonIgnore //이 어노테이션으로 WebSocketSession의 직렬화를 방지
//    private Set<WebSocketSession> sessions;
//
//    @Builder
//    public  ChatRoomResDto(String roomId, String name, LocalDateTime regDate) {
//        this.roomId = roomId;
//        this.name = name;
//        this.regDate = regDate;
//        this.sessions = Collections.newSetFromMap(new ConcurrentHashMap<>());
//    }
//    public void handleActions(WebSocketSession session, ChatMessageDto chatMessage, ChatService chatService) {
//        if (chatMessage.getType() != null && chatMessage.getType().equals(ChatMessageDto.MessageType.ENTER)) {
//            sessions.add(session);
//            if (chatMessage.getSender() != null) {
//                chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
//            }
//            log.debug("New session added: " + session);
//        } else if(chatMessage.getType() != null && chatMessage.getType().equals(ChatMessageDto.MessageType.CLOSE)) {
//            sessions.remove(session);
//            if (chatMessage.getSender() != null) {
//                chatMessage.setMessage(chatMessage.getSender() + "님이 퇴장했습니다.");
//            }
//            log.debug("Session removed: " + session);
//        } else {
//            log.debug("Message received: " + chatMessage.getMessage());
//        }
//        sendMessage(chatMessage, chatService);
//    }
//    private <T> void sendMessage(T message, ChatService chatService) {
//        for (WebSocketSession session : sessions) {
//            try {
//                chatService.sendMessage(session, message);
//            } catch (Exception e) {
//                log.error("Error sending message in ChatRoomResDto: ", e);
//            }
//        }
//    }
//
//}
