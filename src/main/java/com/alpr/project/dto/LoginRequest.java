package com.alpr.project.dto;

import lombok.Data;
import jakarta.validation.constraints.*;
@Data
public class LoginRequest {
    @NotBlank(message = "Email boş olamaz")
    @Email(message = "Geçerli email giriniz")
    private String email;

    @NotBlank(message = "Şifre boş olamaz")
    private String password;
}
