package com.example.springblogapi.controller;

import com.example.springblogapi.dto.LoginRequestDTO;
import com.example.springblogapi.dto.UserRequestDTO;
import com.example.springblogapi.dto.UserResponseDTO;
import com.example.springblogapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
//NOT USED
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        return userService.createUser(userRequestDTO);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

//    @PostMapping("/login")
//    public UserResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
//        return userService.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
//    }
}
