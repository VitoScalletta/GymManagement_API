package org.example.gymmanagement_api.dto.response;

import lombok.Data;
import org.example.gymmanagement_api.entity.Role;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private boolean isActive;
}
