package com.kh.jpatotalapp.repository;

import com.kh.jpatotalapp.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

}