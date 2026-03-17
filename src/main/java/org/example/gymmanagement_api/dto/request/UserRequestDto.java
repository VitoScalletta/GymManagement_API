package org.example.gymmanagement_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDto {

    @NotBlank(message = "İsim boş bırakılamaz")
    private String firstName;

    @NotBlank(message = "Soyisim boş bırakılamaz")
    private String lastName;

    @NotBlank(message = "Email boş bırakılamaz")
    @Email(message = "Geçerli bir email giriniz")
    private String email;

    @NotBlank(message = "Şifre boş olamaz")
    @Size(min =  8,message = "En az 8 karakter olmalı")
    private String password;
}
