package com.example.springblogapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDTO {
    private Long id;
    private String content;
    private String username;
    private LocalDateTime localDateTime;
}
