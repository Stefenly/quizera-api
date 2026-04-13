package co.istad.quizera.project.service;

import co.istad.quizera.project.dto.flashcard.FlashcardItemDto;

import java.util.List;

public interface FlashcardItemService {

    FlashcardItemDto create(Long flashcardId, FlashcardItemDto dto);

    List<FlashcardItemDto> getByFlashcard(Long flashcardId);

    FlashcardItemDto update(Long id, FlashcardItemDto dto);

    void delete(Long id);
}