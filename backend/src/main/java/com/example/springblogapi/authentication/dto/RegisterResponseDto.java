package com.example.springblogapi.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDto {
    private Long id;
    private String username;
    private String email;
}
