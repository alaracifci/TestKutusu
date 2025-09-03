package com.testkutusu.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestDto {

    @NotBlank(message = "Başlık boş olamaz")
    String title;

    String description;  //açıklama isteğe bağlı

    @NotNull(message = "Aktiflik durumu belirtilmeli")
    Boolean active;

    LocalDateTime createdAt;


}
