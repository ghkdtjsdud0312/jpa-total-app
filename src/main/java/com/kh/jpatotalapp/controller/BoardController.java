package com.kh.jpatotalapp.controller;

import com.kh.jpatotalapp.dto.BoardDto;
import com.kh.jpatotalapp.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kh.jpatotalapp.utils.Common.CORS_ORIGIN;

@Slf4j // 자동으로 log 변수를 선언하여 편리하게 log를 찍을 수 있음
@CrossOrigin(origins = CORS_ORIGIN) // 웹 페이지의 제한된 자원을 외부 도메인에서 접근을 허용해주는 매커니즘
@RestController // Restuful 웹서비스의 컨트롤러
@RequestMapping("/api/board") // 특정 uri로 요청을 보내면 Controller에서 어떠한 방식으로 처리할지 정의, 이때 들어온 요청을 특정 메서드와 매핑하기 위해 사용하는 것
@RequiredArgsConstructor // 새로운 필드를 추가할 때 다시 생성자를 만들어서 관리해야하는 번거로움을 없애줌 (@Autowired를 사용하지 않고 의존성 주입)

public class BoardController { // 프론트와 백 연결해서 받아옴, 프레젠테이션 레이어, 웹 요청과 응답을 처리함
    private final BoardService boardService;
    // 게시글 등록
    @PostMapping("/new")
    public ResponseEntity<Boolean> boardRegister(@RequestBody BoardDto boardDto) { // 요청본문 requestBody, 응답본문 responseBody(비동기식 클라-서버 통신을 위해 JSON 형식의 데이터를 주고받는 것)
        boolean isTrue = boardService.saveBoard(boardDto);
        return ResponseEntity.ok(isTrue);
    }

    //게시글 수정
    // PathVariable : 경로변수는 중괄호 {id}로 둘러싸인 값(조회, 수정, 삭제에 사용)
    @PutMapping("/modify/{id}")
    public ResponseEntity<Boolean> boardModify(@PathVariable Long id,@RequestBody BoardDto boardDto) {
        boolean isTrue = boardService.modifyBoard(id, boardDto);
        return ResponseEntity.ok(isTrue);
    }

    // 게시물 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> boardDelete(@PathVariable Long id) {
        boolean isTrue = boardService.deleteBoard(id);
        return ResponseEntity.ok(isTrue);
    }
    //게시물 목록 조회
    @GetMapping("/list")
    public ResponseEntity<List<BoardDto>> boardList() { // <List<BoardDto>> 여러개가 있으므로 list 사용
        List<BoardDto> list = boardService.getBoardList(); // getBoardList() 가져오는 것
        return ResponseEntity.ok(list);
    }
    //게시글 목록 페이징
    @GetMapping("/list/page")
    public ResponseEntity<List<BoardDto>> boardList(@RequestParam(defaultValue = "0") int page, // RequestParam : 사용자가 전달하는 값을 1:1로 매핑해주는 어노테이션
                                                    @RequestParam(defaultValue = "20") int size) {
        List<BoardDto> list = boardService.getBoardList(page, size);
        return ResponseEntity.ok(list);
    }
    //게시글 상세 조회
    @GetMapping("/detail/{id}") // 하나만 선택
    public ResponseEntity<BoardDto> boardDetail(@PathVariable Long id) {
        BoardDto boardDto = boardService.getBoardDetail(id);
        return ResponseEntity.ok(boardDto);
    }
    // 게시글 검색
     @GetMapping("/search")
    public ResponseEntity<List<BoardDto>> boardSearch(@RequestParam String keyword) {
        List<BoardDto> list = boardService.searchBoard(keyword);
        return ResponseEntity.ok(list);
     }
     //페이지 수 조회
    @GetMapping("/count")
    public ResponseEntity<Integer> listBoards(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Integer pageCnt = boardService.getBoards(pageRequest);
        return ResponseEntity.ok(pageCnt);
    }


}
