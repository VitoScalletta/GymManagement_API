package org.example.gymmanagement_api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MembershipPlanRequestDto {
    @NotBlank(message = "İsim boş bırakılamaz")
    private String name;

    @NotNull(message = "Paket Süresi boş bırakılamaz")
    @Min(value = 1,message = "En az 1 aylık paket kalmalı")
    private Integer durationInMonths;

    @NotNull(message = "Fiyat boş bırakılamaz")
    @Positive(message = "Fiyat sıfırdan büyük olmalı")
    private Double price;
}
