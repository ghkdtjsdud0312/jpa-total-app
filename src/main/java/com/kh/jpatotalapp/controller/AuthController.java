package com.kh.jpatotalapp.controller;

import com.kh.jpatotalapp.dto.MemberReqDto;
import com.kh.jpatotalapp.dto.MemberResDto;
import com.kh.jpatotalapp.dto.TokenDto;
import com.kh.jpatotalapp.service.AuthService;
import com.kh.jpatotalapp.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController // controller + ResponseBody -> Json 형태로 객체 데이터 반환 하는 것 /데이터를 응답으로 제공하는 REST API를 개발할 때 주로 사용하며 객체를 ResponseEntity로 감싸서 반환
@RequestMapping("/auth") // 특정 url로 요청(request)을 보내면 Controller에서 어떠한 방식으로 처리할지 정의함
@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션

public class AuthController {  // 프론트와 백 연결해서 받아옴, 프레젠테이션 레이어, 웹 요청과 응답을 처리함
    private final AuthService authService;
    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup") // 주어진 URI 표현식과 일치하는 HTTP POST 요청을 처리(추가/등록)
    //@RequestBody : HttpRequest의 본문 requestBody의 내용을 자바 객체로 매핑하는 역할
    // ResponseEntity : HttpEntity를 상속 받고 사용자의 응답 데이터가 포함된 클래스로 HttpStatus,HttpHeaders,HttpBody 포함
    public ResponseEntity<MemberResDto> signup(@RequestBody MemberReqDto requestDto) {
        return ResponseEntity.ok(authService.signup(requestDto));
    }
    // 로그인
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberReqDto requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }
    // 회원 존재 여부 확인
    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> memberExists(@PathVariable String email) {
        boolean isTrue = memberService.isMember(email);
        return ResponseEntity.ok(!isTrue);
    }
}