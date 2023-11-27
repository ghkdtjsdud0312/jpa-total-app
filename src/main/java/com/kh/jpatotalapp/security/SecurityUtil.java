package com.kh.jpatotalapp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    private SecurityUtil() {
    }
    // SecurityContext의 Authentication 객체를 이용해 회원의 정보를 가져옴
    // Request가 들어올 때 JwtFilter의 doFilter에서 저장
    public static Long getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("Security Context에 인증 정보가 없습니다.");
        }
        return Long.parseLong(authentication.getName());
    }
//    // Security Context의 Authentication 객체를 이용해 회원의 이메일을 가져온다.
//    public static String getCurrentMemberEmail() {
//        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication.getName() == null) {
//            throw new RuntimeException("Security Context에 인증 정보가 없습니다.");
//        }
//        return authentication.getName();
//    }
//    // Security Context의 Authentication 객체를 이용해 회원의 이름을 가져온다.
//    public static String getCurrentMemberName() {
//        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication.getName() == null) {
//            throw new RuntimeException("Security Context에 인증 정보가 없습니다.");
//        }
//        return authentication.getPrincipal().toString();
//    }

}