package com.testkutusu.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuestionDto {

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
