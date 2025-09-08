package com.testkutusu.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerDto {

    Long id;
    @NotNull(message = "text gerekli")
    String text;
    @NotNull(message = "Question id gerekli")
    Long questionId;

    @NotNull(message = "öğrencinin cevabı gerekli")
    String studentAnswer;

    Boolean isCorrect;

}
