package com.testkutusu.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionDto {

    Long id;

    @NotBlank(message = "question text isrequired")
    String text;

    String correctAnswer;

    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String optionE;

    Long testId;
}
