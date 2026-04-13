package co.istad.quizera.project.dto.classroom;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinClassRequest {
    private String classCode;
}