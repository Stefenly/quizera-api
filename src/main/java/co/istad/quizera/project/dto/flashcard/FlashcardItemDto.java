package co.istad.quizera.project.dto.flashcard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlashcardItemDto {

    private Long id;

    @NotBlank(message = "Front text is required")
    @Size(max = 255, message = "Front text must be less than 255 characters")
    private String frontText;

    @NotBlank(message = "Back text is required")
    @Size(max = 255, message = "Back text must be less than 255 characters")
    private String backText;

    private String imageUrl;
}