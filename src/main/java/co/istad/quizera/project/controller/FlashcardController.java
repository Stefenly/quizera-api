package co.istad.quizera.project.controller;

import co.istad.quizera.project.dto.flashcard.FlashcardCreateRequest;
import co.istad.quizera.project.dto.flashcard.FlashcardResponse;
import co.istad.quizera.project.entity.User;
import co.istad.quizera.project.repository.UserRepository;
import co.istad.quizera.project.service.FlashcardService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flashcards")
@RequiredArgsConstructor
public class FlashcardController {

    private final FlashcardService flashcardService;

    // CREATE (AUTH REQUIRED)
    @PostMapping("/{userId}")
    public FlashcardResponse create(
            @PathVariable Long userId,
            @RequestBody @Valid FlashcardCreateRequest request
    ) {
        return flashcardService.create(userId, request);
    }

    // PUBLIC (GUEST CAN ACCESS)
    @GetMapping("/public")
    public List<FlashcardResponse> getPublicFlashcards() {
        return flashcardService.getPublicFlashcards();
    }

    // SINGLE FLASHCARD
    @GetMapping("/{id}")
    public FlashcardResponse get(@PathVariable Long id) {
        return flashcardService.getById(id);
    }

    // ALL (ADMIN / AUTH)
    @GetMapping
    public List<FlashcardResponse> getAll() {
        return flashcardService.getAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public FlashcardResponse update(
            @PathVariable Long id,
            @RequestBody @Valid FlashcardCreateRequest request
    ) {
        return flashcardService.update(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        flashcardService.delete(id);
        return "Deleted successfully";
    }
}