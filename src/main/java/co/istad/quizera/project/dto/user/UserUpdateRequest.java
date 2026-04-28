package co.istad.quizera.project.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequest {

    @Size(min = 2, max = 100, message = "Name must be 2–100 characters")
    private String name;

    @Email(message = "Invalid email format")
    @Size(max = 150, message = "Email must be less than 150 characters")
    private String email;
}