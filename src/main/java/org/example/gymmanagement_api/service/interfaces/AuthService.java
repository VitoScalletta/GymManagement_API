package org.example.gymmanagement_api.service.interfaces;

import org.example.gymmanagement_api.dto.request.LoginRequestDto;
import org.example.gymmanagement_api.dto.response.AuthResponseDto;

public interface AuthService {
    AuthResponseDto login(LoginRequestDto loginRequestDto);
}
