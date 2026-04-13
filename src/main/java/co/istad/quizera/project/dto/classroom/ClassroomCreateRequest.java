package co.istad.quizera.project.dto.classroom;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassroomCreateRequest {
    private String name;
}