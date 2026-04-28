package co.istad.quizera.project.dto.quiz;

import co.istad.quizera.project.enums.Visibility;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResponse {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private Long classroomId;

    @NotNull
    private Long createdById;

    @NotNull
    private Visibility visibility;

    @NotNull
    private LocalDateTime createdAt;

    private Integer totalQuestions;

    private Integer durationInSeconds;
}