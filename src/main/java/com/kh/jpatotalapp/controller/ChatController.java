package com.kh.jpatotalapp.controller;

import com.kh.jpatotalapp.dto.ChatRoomResDto;
import com.kh.jpatotalapp.dto.ChatRoomReqDto;
import com.kh.jpatotalapp.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.kh.jpatotalapp.utils.Common.CORS_ORIGIN;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = CORS_ORIGIN)
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    @PostMapping("/new")
    // 채팅방 새로 만들기
    public ResponseEntity<String> createRoom(@RequestBody ChatRoomReqDto chatRoomDto) { // RequestBody 요청부문
        log.warn("chatRoomDto : {}", chatRoomDto);
        log.warn("chatRoomDto : {}", chatRoomDto.getName());
        log.warn("chatRoomDto : {}", chatRoomDto.getEmail());
        ChatRoomResDto room = chatService.createRoom(chatRoomDto.getName()); // ReponseBody 반응부분
        System.out.println(room.getRoomId());
        return new ResponseEntity<>(room.getRoomId(), HttpStatus.OK);
    }
    @GetMapping("/list")
    public List<ChatRoomResDto> findAllRoom() {
        return chatService.findAllRoom();
    }

    // 방 정보 가져오기
    @GetMapping("/room/{roomId}")
    public ChatRoomResDto findRoomById(@PathVariable String roomId) {

        return chatService.findRoomById(roomId);
    }
}