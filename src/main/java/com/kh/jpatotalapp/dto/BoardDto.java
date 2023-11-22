package com.kh.jpatotalapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDto { // 자바용 데이터 객체
    private Long BoardId;
    private String email;
    private Long categoryId;
    private String title;
    private String content;
    private String img;
    private LocalDateTime regDate;
}
