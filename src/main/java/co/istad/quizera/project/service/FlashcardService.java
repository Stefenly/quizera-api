package co.istad.quizera.project.service;

import co.istad.quizera.project.dto.flashcard.FlashcardCreateRequest;
import co.istad.quizera.project.dto.flashcard.FlashcardResponse;

import java.util.List;

public interface FlashcardService {

    FlashcardResponse create(Long userId, FlashcardCreateRequest request);
    FlashcardResponse getById(Long id);
    List<FlashcardResponse> getAll();
    List<FlashcardResponse> getPublicFlashcards();
    FlashcardResponse update(Long id, FlashcardCreateRequest request);
    void delete(Long id);
}