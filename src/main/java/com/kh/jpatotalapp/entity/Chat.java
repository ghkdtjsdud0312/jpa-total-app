package com.kh.jpatotalapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "chat")
@NoArgsConstructor
        public class Chat {
            @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
            private String email;
            private String roomId;
            private String sender;
            private String message;
            private String name;
            private LocalDateTime regDate;

            @PrePersist // DB에 해당 테이블의 insert 연산을 실행할 때 같이 실행
            public void prePersist() {

            regDate = LocalDateTime.now();


        }





}
