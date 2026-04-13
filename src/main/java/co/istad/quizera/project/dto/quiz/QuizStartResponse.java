package co.istad.quizera.project.dto.quiz;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuizStartResponse {
    private Long quizId;
    private Integer durationInSeconds;
    private Long startedAt;
}
