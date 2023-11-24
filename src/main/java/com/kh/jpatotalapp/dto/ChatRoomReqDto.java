package com.kh.jpatotalapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomReqDto { // 채팅방 누가 보냈는지 볼 때 이메일과 이름이 뜸
    private String email; // 이메일
    private String name; // 이름
}
