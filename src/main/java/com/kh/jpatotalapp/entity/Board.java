package com.kh.jpatotalapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "board")
@Getter
@Setter
@ToString // toString 메소드를 자동으로 생성
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 만들어줌
public class Board { // DB테이블 생성
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // 기본 설정 값으로 각 데이터베이스에 따라 기본키를 자동으로 생성함(null이면 안되고 유일하게 식별할 수 있어야 하며 변하지 않는 값이어야 함)
    private Long boardId; // @NoArgsConstructor 선언시 쓸 수 있는 구조
    private String title;
    private String content;
    private String imgPath;
    private LocalDateTime regDate;
    @PrePersist // DB에 해당 테이블의 insert 연산을 실행할 때 같이 실행
    public void prePersist() {
        regDate = LocalDateTime.now();
    }

    // 회원
    @ManyToOne(fetch = FetchType.LAZY) // 지연 전략(JOIN을 하지 않고 Order를 조회하며, 프록시를 사용하는 것을 볼 수 있음)
    @JoinColumn(name = "member_id") // 외래키
    private Member member; // 작성자

    // 카테고리 추가
    @ManyToOne(fetch = FetchType.LAZY) // 지연 전략
    @JoinColumn(name = "category_id") //외래키
    private Category category; // 카테고리

    //게시글이 삭제될 때 해당 게시글의 댓글들이 같이 삭제되는 기능을 적용하고 싶을 때 유용하게 사용할 수 있는 기능
    //Board와 Comment는 1:N 관계, mappedBy는 MappedBy가 정의되지 않은 객체가 주인(Owner)가 되는 것(외래키를 가진 객체를 주인으로 정의하는 것이 좋음)
    //cascade = CascadeType.ALL :  모두 적용되고 부모엔티티의 모든 영속성 상태변화가 자식엔티티에 반영됨
    // orphanRemoval = true :부모 엔티티와의 관계가 끊어진 자식 엔티티를 삭제
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments; //댓글 목록
}
