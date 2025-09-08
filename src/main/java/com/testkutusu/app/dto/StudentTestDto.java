package com.testkutusu.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public class StudentTestDto {

    Long id;

    @NotNull(message = "Öğrenci ID'si gerekli")
    Long studentId;

    @NotNull(message = "test ID'si gerekli")
    Long testId;

    Double score;
    LocalDateTime participationDate;
    List<AnswerDto> answers;
    List<Long> answerIds;
}
