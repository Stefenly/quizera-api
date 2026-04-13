package co.istad.quizera.project.dto.quiz;

import co.istad.quizera.project.enums.McqOption;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResultDto {

    private Long questionId;
    private String questionText;

    private McqOption correctAnswer;
    private McqOption selectedAnswer;

    private boolean isCorrect;
}