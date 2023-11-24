package com.kh.jpatotalapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {
    public enum MessageType{
        ENTER, TALK, CLOSE
    }
    private MessageType type;
    private String roomId; // 방 이름
    private String sender; // 보내는 사람
    private String message; // 메세지
}
