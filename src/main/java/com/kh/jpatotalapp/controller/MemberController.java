package com.kh.jpatotalapp.controller;
import com.kh.jpatotalapp.dto.MemberDto;
import com.kh.jpatotalapp.entity.Board;
import com.kh.jpatotalapp.entity.Member;
import com.kh.jpatotalapp.repository.MemberRepository;
import com.kh.jpatotalapp.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kh.jpatotalapp.utils.Common.CORS_ORIGIN;

@Slf4j
@CrossOrigin(origins = CORS_ORIGIN)
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    // 회원 전체 조회
    @GetMapping("/list")
    public ResponseEntity<List<MemberDto>> memberList() {
        List<MemberDto> list = memberService.getMemberList();
        return ResponseEntity.ok(list);
    }
    // 총 페이지 수
    @GetMapping("/list/count")
    public ResponseEntity<Integer> memberCount(@RequestParam(defaultValue = "20") int page,
                                               @RequestParam(defaultValue = "0") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        int pageCnt = memberService.getMemberPage(pageRequest);
        return ResponseEntity.ok(pageCnt);
    }
    // 회원 조회 페이지네이션
    @GetMapping("/list/page")
    public ResponseEntity<List<MemberDto>> memberList(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "20") int size) {
        List<MemberDto> list = memberService.getMemberList(page,size);
        return ResponseEntity.ok(list);
    }
    // 회원 상세 조회
    @GetMapping("/detail/{email}")
    public ResponseEntity<MemberDto> memberDetail(@PathVariable String email) {
        MemberDto memberDto = memberService.getMemberDetail(email);
        return ResponseEntity.ok(memberDto);
    }
    // 회원 수정
    @PutMapping("/modify")
    public ResponseEntity<Boolean> memberModify(@RequestBody MemberDto memberDto) {
        log.info("memberDto: {}", memberDto.getEmail());
        boolean isTrue = memberService.modifyMember(memberDto);
        return ResponseEntity.ok(isTrue);
    }
    // 회원 등록
    @PostMapping("/new")
    public ResponseEntity<Boolean> memberRegister(@RequestBody MemberDto memberDto) {
        boolean isTrue = memberService.saveMember(memberDto);
        return ResponseEntity.ok(isTrue);
    }
    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> memberLogin(@RequestBody MemberDto memberDto) {
        boolean isTrue = memberService.login(memberDto.getEmail(),memberDto.getPwd());
        return ResponseEntity.ok(isTrue);
    }
    // 회원 존재 여부 확인
    @GetMapping("/check")
    public ResponseEntity<Boolean> isMember(@RequestParam String email) {
        log.info("email: {}", email);
        boolean isReg = memberService.deleteMember(email);
        return ResponseEntity.ok(isReg);
    }
    // 회원 삭제
    @DeleteMapping("/del/{email}")
    public ResponseEntity<Boolean> memberDelete(@PathVariable String email) {
        boolean isTrue = memberService.deleteMember(email);
        return ResponseEntity.ok(isTrue);
    }
}
