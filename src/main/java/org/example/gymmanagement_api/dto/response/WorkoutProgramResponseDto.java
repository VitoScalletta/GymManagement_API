package org.example.gymmanagement_api.dto.response;

import lombok.Data;
import org.example.gymmanagement_api.dto.request.ExerciseRequestDto;
import org.example.gymmanagement_api.entity.User;

import java.util.List;

@Data
public class WorkoutProgramResponseDto {
    private Long id;
    private String name;
    private String description;
    private UserResponseDto user;
    private List<ExerciseResponseDto> exercises;

}
