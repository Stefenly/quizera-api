package co.istad.quizera.project.dto.quiz;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuizStartResponse {

    @NotNull
    private Long quizId;

    @NotNull
    private Integer durationInSeconds;

    @NotNull
    private Long startedAt;
}