package org.example.gymmanagement_api.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private boolean isActive;
}
