package com.kh.jpatotalapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private Long commentId;
    private Long boardId;
    private String email;
    private String content;
    private LocalDateTime regDate;
}
