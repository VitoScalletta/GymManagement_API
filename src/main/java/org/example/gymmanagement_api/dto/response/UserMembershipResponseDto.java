package org.example.gymmanagement_api.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserMembershipResponseDto {
    Long id;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
    boolean isActive;
    MembershipPlanResponseDto plan;
}
