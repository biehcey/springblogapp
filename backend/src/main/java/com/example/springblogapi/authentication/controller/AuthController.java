package com.example.springblogapi.authentication.controller;



import com.example.springblogapi.authentication.dto.LoginResponseDto;
import com.example.springblogapi.authentication.dto.RegisterResponseDto;
import com.example.springblogapi.authentication.dto.UserLoginDto;
import com.example.springblogapi.authentication.dto.UserRegisterDto;
import com.example.springblogapi.authentication.entity.SecureUser;
import com.example.springblogapi.authentication.service.AuthenticationService;
import com.example.springblogapi.authentication.service.JwtService;
import com.example.springblogapi.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody UserRegisterDto dto){
        User registeredUser = authenticationService.signup(dto);

        RegisterResponseDto responseDto = new RegisterResponseDto();
        responseDto.setId(registeredUser.getId());
        responseDto.setUsername(registeredUser.getUsername());
        responseDto.setEmail(registeredUser.getEmail());

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody UserLoginDto dto){
        User user = authenticationService.login(dto);
        SecureUser secureUser = new SecureUser(user);
        String jwtToken = jwtService.generateToken(secureUser);

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(jwtToken);
        loginResponseDto.setExpiresIn(jwtService.getExpirationTime());
        loginResponseDto.setCreatedAt(user.getCreatedAt());
        loginResponseDto.setUserId(user.getId());

        return ResponseEntity.ok(loginResponseDto);
    }


}
