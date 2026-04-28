package co.istad.quizera.project.dto.flashcard;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlashcardResponse {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String visibility;

    @NotNull
    private Long createdById;

    @NotNull
    private LocalDateTime createdAt;

    private List<FlashcardItemDto> items;
}