package co.istad.quizera.project.dto.flashcard;

import lombok.*;

@Getter
@Setter
@Builder
public class FlashcardItemDto {
    private Long id;
    private String frontText;
    private String backText;
    private String imageUrl;
}