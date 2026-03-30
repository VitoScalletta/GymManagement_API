package org.example.gymmanagement_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.gymmanagement_api.dto.request.LoginRequestDto;
import org.example.gymmanagement_api.dto.response.AuthResponseDto;
import org.example.gymmanagement_api.security.CustomUserDetailsService;
import org.example.gymmanagement_api.security.JwtService;
import org.example.gymmanagement_api.service.interfaces.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword())
        );

        var userDetails = customUserDetailsService.loadUserByUsername(loginRequestDto.getEmail());
        var token = jwtService.generateToken(userDetails);
        return new AuthResponseDto(token);
    }
}
