package com.kh.jpatotalapp.dto;
import com.kh.jpatotalapp.constant.Authority;
import com.kh.jpatotalapp.entity.Member;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자
@NoArgsConstructor // 기본 생성자
@Builder // 빌더 패턴
public class MemberReqDto {
    private String email;
    private String password;
    private String name;
    private String image;
    // MemberReqDto -> Member
    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .image(image)
                .authority(Authority.ROLE_USER)
                .build();
    }
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}