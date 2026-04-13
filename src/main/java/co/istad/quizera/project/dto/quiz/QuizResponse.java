package co.istad.quizera.project.dto.quiz;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResponse {
    private Long id;
    private String title;
    private Long classId;
    private Long createdById;
    private Boolean isPublic;
    private LocalDateTime createdAt;
    private Integer totalQuestions;
}