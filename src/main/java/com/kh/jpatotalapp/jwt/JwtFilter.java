package com.kh.jpatotalapp.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
// 유효성 검사
@Slf4j
@RequiredArgsConstructor // final이 붙은 필드를 인자 값으로 하는 생성자를 만들어줌
// OncePerRequestFilter : Http Request의 한번의 요청에 대해 한 번만 실행하는 Filter(인터페이스를 구현하기 때문에 요청 받을 때 단 한번만 실행)
public class JwtFilter  extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization"; // 토큰을 요청 헤더의 Authorization 키에 담아서 전달
    public static final String BEARER_PREFIX = "Bearer "; // 토큰 앞에 붙는 문자열(영문자+공백까지 7글자)
    private final TokenProvider tokenProvider; // 토큰 생성, 토큰 검증을 수행 하는 TokenProvider

    // Request Header 에서 토큰 정보를 꺼내오기
    private String resolveToken(HttpServletRequest request) { // 토큰을 요청 헤더에서 꺼내오는 메서드,유효한 토큰인지 확인
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER); // 헤더에서 토큰 꺼내오기
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) { // 토큰이 존재하고, 토큰 앞에 붙는 문자열이 존재하면
            return bearerToken.substring(7); // 토큰 앞에 붙는 문자열을 제거하고 토큰 반환
        }
        return null;
    }

    // doFilterInternal : 실제 필터링 로직을 수행하는 곳
    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext 에 저장하는 역할 수행
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = resolveToken(request); // Request Header에서 토큰을 꺼냄

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {  // validateToken으로 토큰 유효성 검사
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication); // 정상 토큰이면 해당 토큰으로 Authentication을 가져와서 SecurityContext 에 저장
        }

        filterChain.doFilter(request, response);
    }
}

// Request Header 에서 Access Token 을 꺼내고 여러가지 검사 후 유저 정보를 꺼내서 SecurityContext 에 저장합니다.
//가입/로그인/재발급을 제외한 모든 Request 요청은 이 필터를 거치기 때문에 토큰 정보가 없거나 유효하지 않으면 정상적으로 수행되지 않습니다.
//그리고 요청이 정상적으로 Controller 까지 도착했다면 SecurityContext 에 Member ID 가 존재한다는 것이 보장됩니다.
//대신 직접 DB 를 조회한 것이 아니라 Access Token 에 있는 Member ID 를 꺼낸 거라서, 탈퇴로 인해 Member ID 가 DB 에 없는 경우 등 예외 상황은 Service 단에서 고려해야 합니다.