package org.example.gymmanagement_api.service.interfaces;

import jakarta.validation.Valid;
import org.example.gymmanagement_api.dto.request.UserRequestDto;
import org.example.gymmanagement_api.dto.response.UserResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long id);
    UserResponseDto updateUser(Long id,UserRequestDto userRequestDto);
    void deleteUserById(Long id);

}
