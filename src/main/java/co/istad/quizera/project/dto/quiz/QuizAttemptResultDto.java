package co.istad.quizera.project.dto.quiz;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAttemptResultDto {

    @NotNull
    private Long id;

    @NotNull
    private Long quizId;

    @NotNull
    private Integer score;

    @NotNull
    private Integer totalQuestions;

    @NotNull
    private LocalDateTime completedAt;

    private List<QuestionResultDto> results;

    private GamificationResultDto gamification;
}