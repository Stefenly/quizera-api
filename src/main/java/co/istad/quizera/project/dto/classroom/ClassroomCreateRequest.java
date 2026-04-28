package co.istad.quizera.project.dto.classroom;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassroomCreateRequest {

    @NotBlank(message = "Classroom name is required")
    @Size(min = 2, max = 100, message = "Classroom name must be 2–100 characters")
    private String name;
}