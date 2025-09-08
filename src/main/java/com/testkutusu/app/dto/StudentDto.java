package com.testkutusu.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StudentDto(
        @NotBlank(message = "İsim boş olamaz")
        String firstName,

        @NotBlank(message = "Soyad boş olamaz")
        String lastName,

        @NotBlank(message = "Öğrenci numarası boş olamaz")
        String studentNumber,

        @Email(message = "Geçerli bir email giriniz")
        String email,

        @Size(min = 8, message = "Şifre en az 8 karakter olmalı")
        String password
) {}



