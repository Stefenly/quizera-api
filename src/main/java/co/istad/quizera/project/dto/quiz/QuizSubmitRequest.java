package co.istad.quizera.project.dto.quiz;

import co.istad.quizera.project.enums.McqOption;
import lombok.*;
import java.util.List;

@Getter
@Setter
public class QuizSubmitRequest {

    private Long quizId;

    private Long startedAt;

    private List<AnswerDto> answers;

    @Getter
    @Setter
    public static class AnswerDto {
        private Long questionId;
        private McqOption selectedAnswer;
    }
}