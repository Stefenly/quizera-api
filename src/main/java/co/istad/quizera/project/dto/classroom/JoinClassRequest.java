package co.istad.quizera.project.dto.classroom;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinClassRequest {

    @NotBlank(message = "Class code is required")
    @Size(min = 4, max = 20, message = "Class code must be 4–20 characters")
    private String classCode;
}