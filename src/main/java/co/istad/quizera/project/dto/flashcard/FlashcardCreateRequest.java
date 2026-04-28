package co.istad.quizera.project.dto.flashcard;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlashcardCreateRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 150, message = "Title must be 2–150 characters")
    private String title;

    @NotBlank(message = "Visibility is required (PUBLIC or PRIVATE)")
    private String visibility;

    @NotEmpty(message = "Flashcard must have at least one item")
    private List<@Valid FlashcardItemDto> items;
}