package com.kh.jpatotalapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor // 파라미터가 없는 디폴트 생성자를 생성
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 생성
@Builder // 클래스 레벨에 붙이거나 생성자에 붙여주면 파라미터를 활용하여 빌더 패턴을 자동으로 생성해줌
public class TokenDto {
    private String grantType; // 인증 방식
    private String accessToken; // 액세스 토큰
    private String refreshToken; // 리프레시 토큰
    private Long accessTokenExpiresIn; // 액세스 토큰 만료 시간(로그인 후 유효기한이 있는 것-짧음)
    private Long refreshTokenExpiresIn; // 리프레시 토큰 만료 시간(다시 리렌더링해서 유효기간이 새로 늘어남)
}