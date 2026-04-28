package co.istad.quizera.project.dto.quiz;

import co.istad.quizera.project.enums.McqOption;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Getter
@Setter
public class QuizSubmitRequest {

    @NotNull(message = "Quiz ID is required")
    private Long quizId;

    @NotNull(message = "Start time is required")
    private Long startedAt;

    @NotEmpty(message = "Answers cannot be empty")
    private List<@Valid AnswerDto> answers;

    @Getter
    @Setter
    public static class AnswerDto {

        @NotNull(message = "Question ID is required")
        private Long questionId;

        @NotNull(message = "Selected answer is required")
        private McqOption selectedAnswer;
    }
}