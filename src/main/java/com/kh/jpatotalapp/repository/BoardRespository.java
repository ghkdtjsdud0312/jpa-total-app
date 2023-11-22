package com.kh.jpatotalapp.repository;

import com.kh.jpatotalapp.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRespository extends JpaRepository<Board, Long> {
    List<Board> findByTitleContaining(String keyword);
    Page<Board> findAll(Pageable pageable);
}
