package com.kh.jpatotalapp.repository;

import com.kh.jpatotalapp.entity.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChattingRepository extends JpaRepository <Chatting, Long> {
}
