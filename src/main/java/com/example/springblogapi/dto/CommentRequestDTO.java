package com.example.springblogapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequestDTO {
    @NotBlank
    private String content;

    @NotNull
    private Long userId;

    @NotNull
    private Long postId;
}
