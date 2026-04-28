package co.istad.quizera.project.controller;

import co.istad.quizera.project.dto.flashcard.FlashcardItemDto;
import co.istad.quizera.project.service.FlashcardItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flashcard-items")
@RequiredArgsConstructor
public class FlashcardItemController {

    private final FlashcardItemService service;

    @PostMapping("/{flashcardId}")
    public FlashcardItemDto create(
            @Valid
            @PathVariable Long flashcardId,
                                   @RequestBody FlashcardItemDto dto) {
        return service.create(flashcardId, dto);
    }

    @GetMapping("/flashcard/{flashcardId}")
    public List<FlashcardItemDto> get(@PathVariable Long flashcardId) {
        return service.getByFlashcard(flashcardId);
    }

    @PutMapping("/{id}")
    public FlashcardItemDto update(
            @Valid
            @PathVariable Long id,
                                   @RequestBody FlashcardItemDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted";
    }
}
