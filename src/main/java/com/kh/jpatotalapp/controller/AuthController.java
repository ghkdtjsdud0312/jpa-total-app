package com.kh.jpatotalapp.controller;

import com.kh.jpatotalapp.dto.MemberReqDto;
import com.kh.jpatotalapp.dto.MemberResDto;
import com.kh.jpatotalapp.dto.TokenDto;
import com.kh.jpatotalapp.service.AuthService;
import com.kh.jpatotalapp.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.kh.jpatotalapp.utils.Common.CORS_ORIGIN;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = CORS_ORIGIN)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
//    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResDto> signup(@RequestBody MemberReqDto requestDto) {
        return ResponseEntity.ok(authService.signup(requestDto));
    }
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberReqDto requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }
//    // 회원 존재 여부 확인
//    @GetMapping("/exists/{email}")
//    public ResponseEntity<Boolean> memberExists(@PathVariable String email) {
//        boolean isTrue = memberService.isMember(email);
//        return ResponseEntity.ok(!isTrue);
//    }
}