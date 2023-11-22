package com.kh.jpatotalapp.repository;

import com.kh.jpatotalapp.entity.Board;
import com.kh.jpatotalapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByContentContaining(String keyword);
    List<Comment> findByBoard(Board board);
}
