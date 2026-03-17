package org.example.gymmanagement_api.dto.response;

import lombok.Data;

@Data
public class ExerciseResponseDto {
    private Long id;
    private String name;
    private int sets;
    private int reps;

}
