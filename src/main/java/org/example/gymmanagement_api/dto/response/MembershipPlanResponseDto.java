package org.example.gymmanagement_api.dto.response;

import lombok.Data;

@Data
public class MembershipPlanResponseDto {
    private Long id;
    private String name;
    private Integer durationInMonths;
    private Double price;
}
