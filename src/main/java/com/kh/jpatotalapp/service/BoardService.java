package com.kh.jpatotalapp.service;

import com.kh.jpatotalapp.dto.BoardDto;
import com.kh.jpatotalapp.entity.Board;
import com.kh.jpatotalapp.entity.Category;
import com.kh.jpatotalapp.entity.Member;
import com.kh.jpatotalapp.repository.BoardRespository;
import com.kh.jpatotalapp.repository.CategoryRepository;
import com.kh.jpatotalapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// 서비스 레이어, 내부에서 자바 로직을 처리함
 // controller에서 객체를 받아서 repository를 실행 후 결과 값을 전달(frontEnd -> controller -> service -> repository -> service -> controller)
@Service // 스프링에게 해당 클래스가 Service Component이라고 알려 주는 것 / 작성한 클래스에 @Service 어노테이션을 달아주면 해당 클래스를 루트 컨테이너에 Bean 객체로 생성해줌
@RequiredArgsConstructor // 특정 변수만을 활용하는 생성자를 자동완성 시켜주는 어노테이션, 해당 변수를 final로 선언해도 의존성을 주입받을 수 있음
public class BoardService {
    private final BoardRespository boardRespository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    // 게시글 등록
    public boolean saveBoard(BoardDto boardDto) {
        try{
            Board board = new Board();
            Member member = memberRepository.findByEmail(boardDto.getEmail()).orElseThrow(
                    () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
                );
            Category category = categoryRepository.findById(boardDto.getCategoryId()).orElseThrow(
                    () -> new RuntimeException("해당 카테고리가 존재하지 않습니다.")
            );
            board.setTitle(boardDto.getTitle());
            board.setCategory(category);
            board.setContent(boardDto.getContent());
            board.setImgPath(boardDto.getImg());
            board.setMember(member);
            boardRespository.save(board);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 게시글 전체 조회
    public List<BoardDto> getBoardList() {
       List<Board> boards = boardRespository.findAll();
       List<BoardDto> boardDtos = new ArrayList<>();
       for(Board board : boards) {
           boardDtos.add(convertEntityToDto(board)); // convertEntityToDto : Builder 패턴으로 Entity를 Dto로 변환해주는 Method
       }
       return boardDtos;
    }
    // 게시글 상세 조회
    public BoardDto getBoardDetail(Long id) {
        Board board = boardRespository.findById(id).orElseThrow(
                () -> new RuntimeException("해당 게시글이 존재하지 않습니다.")
        );
        return convertEntityToDto(board);
    }
    // 게시글 수정
    public boolean modifyBoard(Long id, BoardDto boardDto) {
        try {
            Board board = boardRespository.findById(id).orElseThrow(
                    () -> new RuntimeException("해당 게시글이 존재하지 않습니다.")
            );
            board.setTitle(boardDto.getTitle());
            board.setContent(boardDto.getContent());
            board.setImgPath(boardDto.getImg());
            boardRespository.save(board);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 게시물 삭제
    public boolean deleteBoard(Long id) {
        try {
            boardRespository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 게시글 검색
    public List<BoardDto> searchBoard(String keyword) {
        List<Board> boards = boardRespository.findByTitleContaining(keyword);
        List<BoardDto> boardDtos = new ArrayList<>();
        for(Board board : boards) {
            boardDtos.add(convertEntityToDto(board));
        }
        return boardDtos;
    }
    //게시글 페이징
    public List<BoardDto> getBoardList(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        List<Board> boards = boardRespository.findAll(pageable).getContent();
        List<BoardDto> boardDtos = new ArrayList<>();
        for(Board board : boards) {
            boardDtos.add(convertEntityToDto(board));
        }
        return boardDtos;
    }
    // 게시글 엔티티를 DTO로 변환
    private BoardDto convertEntityToDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardId(board.getBoardId());
        boardDto.setTitle(board.getTitle());
        boardDto.setCategoryId(board.getCategory().getCategoryId());
        boardDto.setContent(board.getContent());
        boardDto.setImg(board.getImgPath());
        boardDto.setEmail(board.getMember().getEmail());
        boardDto.setRegDate(board.getRegDate());
        return boardDto;
    }
    // 페이지 수 조회
    public int getBoards(Pageable pageable) {
        return boardRespository.findAll(pageable).getTotalPages();
    }
}
