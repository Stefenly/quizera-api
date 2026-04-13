package co.istad.quizera.project.dto.quiz;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAttemptDto {

    private Long id;
    private Long quizId;
    private Integer score;
    private Integer totalQuestions;
    private LocalDateTime completedAt;
}