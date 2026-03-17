package org.example.gymmanagement_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ExerciseRequestDto {

    @NotBlank(message = "İsim boş bırakılamaz")
    private String name;

    @NotNull(message = "Set boş bırakılamaz")
    @Positive(message = "1den fazla olmalı")
    private Integer sets;
}
