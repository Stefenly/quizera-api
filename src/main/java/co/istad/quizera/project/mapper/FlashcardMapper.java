package co.istad.quizera.project.mapper;

import co.istad.quizera.project.dto.flashcard.FlashcardItemDto;
import co.istad.quizera.project.dto.flashcard.FlashcardResponse;
import co.istad.quizera.project.entity.Flashcard;
import co.istad.quizera.project.entity.FlashcardItem;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlashcardMapper {

    public FlashcardResponse toDto(Flashcard flashcard) {
        return FlashcardResponse.builder()
                .id(flashcard.getId())
                .title(flashcard.getTitle())
                .visibility(flashcard.getVisibility().name())
                .createdById(flashcard.getCreatedBy().getId())
                .createdAt(flashcard.getCreatedAt())
                .items(
                        flashcard.getItems().stream()
                                .map(this::toItemDto)
                                .toList()
                )
                .build();
    }

    public FlashcardItemDto toItemDto(FlashcardItem item) {
        return FlashcardItemDto.builder()
                .id(item.getId())
                .frontText(item.getFrontText())
                .backText(item.getBackText())
                .imageUrl(item.getImageUrl())
                .build();
    }
}