package com.example.springblogapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentRequestDTO {
    @NotBlank
    private String content;

    private Long userId;
}
