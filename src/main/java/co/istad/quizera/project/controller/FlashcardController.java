package co.istad.quizera.project.controller;

import co.istad.quizera.project.dto.flashcard.FlashcardCreateRequest;
import co.istad.quizera.project.dto.flashcard.FlashcardResponse;
import co.istad.quizera.project.entity.User;
import co.istad.quizera.project.repository.UserRepository;
import co.istad.quizera.project.service.FlashcardService;
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

    @PostMapping("/{userId}")
    public FlashcardResponse create(@PathVariable Long userId,
                                    @RequestBody FlashcardCreateRequest request) {
        return flashcardService.create(userId, request);
    }

    @GetMapping("/{id}")
    public FlashcardResponse get(@PathVariable Long id) {
        return flashcardService.getById(id);
    }

    @GetMapping
    public List<FlashcardResponse> getAll() {
        return flashcardService.getAll();
    }

    @PutMapping("/{id}")
    public FlashcardResponse update(@PathVariable Long id,
                                    @RequestBody FlashcardCreateRequest request) {
        return flashcardService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        flashcardService.delete(id);
        return "Deleted successfully";
    }
}