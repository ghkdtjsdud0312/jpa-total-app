package com.kh.jpatotalapp.repository;

import com.kh.jpatotalapp.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 데이터 베이스 쿼리함 실행
// 퍼시스턴스 레이어, 디비나 파일 같은 외부 I/O 작업을 처리함
public interface BoardRespository extends JpaRepository<Board, Long> {
    List<Board> findByTitleContaining(String keyword);
    Page<Board> findAll(Pageable pageable);
    List<Board> findByMemberEmail(String email);
}
