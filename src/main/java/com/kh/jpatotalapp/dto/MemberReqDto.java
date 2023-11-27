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
    public Member toEntity(PasswordEncoder passwordEncoder) { // 비밀번호 암호화, DI
        return Member.builder() // PasswordEncoder를 매개변수로 받아와서 회원의 비밀번호를 암호화하여 Member 객체를 생성(build사용)
                .email(email)
                .password(passwordEncoder.encode(password)) // 암호화 하는 부분, 보안상의 이유로 사용자의 비밀번호를 평문으로 저장하지 않고 암호화된 형태로 저장하기 위한 과정
                .name(name)
                .image(image)
                .authority(Authority.ROLE_USER) // 회원 권한(일반 회원)
                .build(); // Member 객체의 생성이 완료되면, build() 메소드를 호출하여 최종적으로 Member 객체를 생성하고 반환
    }
    // 로그인 기능에서 사용되며, 사용자의 인증 정보를 담고 있는 토큰을 생성하는 데 사용
    // 해당 토큰은 Spring Security의 인증 매커니즘에서 활용되어 사용자를 인증하고 권한을 부여하는 데에 활용
    public UsernamePasswordAuthenticationToken toAuthentication() { // 객체를 생성하여 인증(Authentication)을 나타내는 메소드인 toAuthentication를 정의
        return new UsernamePasswordAuthenticationToken(email, password); // email : 식별정보, password : 자격 증명
    }
}