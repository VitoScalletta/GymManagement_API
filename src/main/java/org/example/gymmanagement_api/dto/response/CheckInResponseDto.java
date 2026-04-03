package org.example.gymmanagement_api.dto.response;

import lombok.Data;
import org.example.gymmanagement_api.entity.User;

import java.time.LocalDateTime;

@Data
public class CheckInResponseDto {
    private Long id;
    private LocalDateTime checkInDateTime;
    private String userFirstName;
    private String userLastName;
}
