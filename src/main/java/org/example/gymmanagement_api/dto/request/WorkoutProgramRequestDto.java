package org.example.gymmanagement_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WorkoutProgramRequestDto {

    @NotBlank(message = "İsim boş bırakılamaz")
    private String name;

    @NotBlank(message = "Açıklama boş bırakılamaz")
    @Size(max = 120,message = "Maximum 120 karakter girebilirsiniz")
    private String description;


}
