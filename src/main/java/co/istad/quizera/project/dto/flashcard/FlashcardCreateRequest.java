package co.istad.quizera.project.dto.flashcard;

import lombok.*;
import java.util.List;

@Getter
@Setter
public class FlashcardCreateRequest {
    private String title;
    private String visibility;
    private List<FlashcardItemDto> items;
}