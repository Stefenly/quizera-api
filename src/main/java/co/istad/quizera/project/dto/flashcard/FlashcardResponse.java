package co.istad.quizera.project.dto.flashcard;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class FlashcardResponse {

    private Long id;
    private String title;
    private String visibility;
    private Long createdById;

    private LocalDateTime createdAt;

    private List<FlashcardItemDto> items;
}