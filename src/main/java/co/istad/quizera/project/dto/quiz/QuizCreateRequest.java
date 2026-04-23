package co.istad.quizera.project.dto.quiz;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizCreateRequest {
    private String title;
    private Long classId;
    private List<QuestionDto> questions;
    private Long classroomId;

    private Boolean isPublic;
}