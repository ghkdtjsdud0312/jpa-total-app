package com.kh.jpatotalapp.repository;

import com.kh.jpatotalapp.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChattingRepository extends JpaRepository <Chat, Long> {
    List<Chat> findRoomById(String roomId);
}
