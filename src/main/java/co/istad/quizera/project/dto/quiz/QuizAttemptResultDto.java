package co.istad.quizera.project.dto.quiz;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAttemptResultDto {

    private Long id;
    private Long quizId;

    private Integer score;
    private Integer totalQuestions;

    private LocalDateTime completedAt;

    private List<QuestionResultDto> results;
    private GamificationResultDto gamification;

}