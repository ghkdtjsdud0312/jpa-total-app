package com.kh.jpatotalapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY) //지연 전략
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY) //지연 전략
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 1000)
    private String content;

    private LocalDateTime regDate;
    @PrePersist
    public void perPersist() {
        regDate = LocalDateTime.now();
    }

}
