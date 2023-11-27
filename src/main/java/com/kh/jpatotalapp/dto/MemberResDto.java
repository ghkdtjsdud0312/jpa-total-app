package com.kh.jpatotalapp.dto;

import com.kh.jpatotalapp.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResDto {
    private String name;
    private String email;
    private String image;
    private LocalDateTime regDate;

    // Member -> MemberResDto
    // 회원 정보를 조회하여 클라이언트에게 전달하기 전에 필요한 정보만을 가지고 있는 응답용 DTO를 생성하는 데 사용되고, 이를 통해 클라이언트와 서버 간의 데이터 전송을 효율적으로 관리
    public static MemberResDto of(Member member) { // Member 객체를 기반으로 하는 응답용 데이터 전송 객체(Data Transfer Object, DTO)를 생성하는 정적(static) 메소드인 of를 정의
        return MemberResDto.builder() //MemberResDto 클래스에 정의된 빌더 패턴을 사용하여 MemberResDto 객체를 생성
                .name(member.getName()) //getName() 메소드를 호출하여 이름을 설정
                .email(member.getEmail()) // getEmail() 메소드를 호출하여 이메일을 설정
                .image(member.getImage()) // getImage() 메소드를 호출하여 이미지 정보를 설정
                .regDate(member.getRegDate()) // getRegDate() 메소드를 호출하여 등록일 정보를 설정
                .build(); // 설정된 정보를 바탕으로 최종적으로 MemberResDto 객체를 생성
    }
}