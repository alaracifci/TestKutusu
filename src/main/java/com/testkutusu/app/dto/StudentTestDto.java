package com.testkutusu.app.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class StudentTestDto {

    @NotNull(message = "Öğrenci ID'si gerekli")
    Long studentId;

    @NotNull(message = "test ID'si gerekli")
    Long testId;

    Double score;
    LocalDateTime participationDate;
    List<AnswerDto> answers;
}
