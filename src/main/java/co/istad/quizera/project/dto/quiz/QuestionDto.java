package co.istad.quizera.project.dto.quiz;

import co.istad.quizera.project.enums.McqOption;
import co.istad.quizera.project.enums.QuestionType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto {

    private Long id;

    private QuestionType type;
    private String questionText;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private McqOption correctAnswer;
    private String imageUrl;
}