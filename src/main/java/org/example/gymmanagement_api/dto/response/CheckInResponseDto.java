package org.example.gymmanagement_api.dto.response;

import org.example.gymmanagement_api.entity.User;

import java.time.LocalDateTime;

public class CheckInResponseDto {
    private Long id;
    private LocalDateTime checkInDateTime;
    private String userFirstName;
    private String userLastName;
}
