package org.example.gymmanagement_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.gymmanagement_api.dto.request.LoginRequestDto;
import org.example.gymmanagement_api.dto.request.UserRequestDto;
import org.example.gymmanagement_api.dto.response.AuthResponseDto;
import org.example.gymmanagement_api.dto.response.UserResponseDto;
import org.example.gymmanagement_api.security.JwtService;
import org.example.gymmanagement_api.service.interfaces.AuthService;
import org.example.gymmanagement_api.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.createUser(userRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
}
