package com.kh.jpatotalapp.entity;

import com.kh.jpatotalapp.constant.Authority;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    private String password;
    @Column(unique = true)
    private String email;
    private String image;
    private LocalDateTime regDate;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Board> boards;

    @Builder
    public Member(String name, String password, String email, String image, Authority authority) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.image = image;
        this.authority = authority;
        this.regDate = LocalDateTime.now();
    }
}
