package org.example.gymmanagement_api.dto.response;

import org.example.gymmanagement_api.entity.User;

import java.util.List;

public class WorkoutProgramResponseDto {
    private Long id;
    private String name;
    private String description;
    private User user;
    private List exercises;

}
