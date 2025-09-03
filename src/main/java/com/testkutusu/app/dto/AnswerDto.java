package com.testkutusu.app.dto;

import jakarta.validation.constraints.NotNull;

public class AnswerDto {

    @NotNull(message = "Question id gerekli")
    Long questionId;

    @NotNull(message = "öğrencinin cevabı gerekli")
    String studentAnswer;

    Boolean isCorrect;

}
