package co.istad.quizera.project.service.impl;

import co.istad.quizera.project.dto.flashcard.FlashcardCreateRequest;
import co.istad.quizera.project.dto.flashcard.FlashcardItemDto;
import co.istad.quizera.project.dto.flashcard.FlashcardResponse;
import co.istad.quizera.project.entity.Flashcard;
import co.istad.quizera.project.entity.FlashcardItem;
import co.istad.quizera.project.entity.User;
import co.istad.quizera.project.enums.Visibility;
import co.istad.quizera.project.repository.FlashcardRepository;
import co.istad.quizera.project.repository.UserRepository;
import co.istad.quizera.project.service.FlashcardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlashcardServiceImpl implements FlashcardService {

    private final FlashcardRepository flashcardRepository;
    private final UserRepository userRepository;

    // CREATE
    @Override
    public FlashcardResponse create(Long userId, FlashcardCreateRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Flashcard flashcard = Flashcard.builder()
                .title(request.getTitle())
                .visibility(Visibility.valueOf(request.getVisibility()))
                .createdBy(user)
                .build();

        if (request.getItems() != null) {

            Set<FlashcardItem> items = request.getItems().stream()
                    .map(dto -> FlashcardItem.builder()
                            .flashcard(flashcard)
                            .frontText(dto.getFrontText())
                            .backText(dto.getBackText())
                            .imageUrl(dto.getImageUrl())
                            .build())
                    .collect(Collectors.toSet());

            flashcard.setItems(items);
        }

//        return mapToResponse(flashcardRepository.save(flashcard));
        Flashcard saved = flashcardRepository.save(flashcard);
        return mapToResponse(saved);
    }

    // GET BY ID
    @Override
    public FlashcardResponse getById(Long id) {
        return flashcardRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    // GET ALL
    @Override
    public List<FlashcardResponse> getAll() {
        return flashcardRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // UPDATE
    @Override
    public FlashcardResponse update(Long id, FlashcardCreateRequest request) {

        Flashcard flashcard = flashcardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        if (request.getTitle() != null)
            flashcard.setTitle(request.getTitle());

        if (request.getVisibility() != null)
            flashcard.setVisibility(Visibility.valueOf(request.getVisibility()));

        // REPLACE ITEMS
        if (request.getItems() != null) {

            flashcard.getItems().clear();

            Set<FlashcardItem> items = request.getItems().stream()
                    .map(dto -> FlashcardItem.builder()
                            .flashcard(flashcard)
                            .frontText(dto.getFrontText())
                            .backText(dto.getBackText())
                            .imageUrl(dto.getImageUrl())
                            .build())
                    .collect(Collectors.toSet());

            flashcard.getItems().addAll(items);
        }

//      return mapToResponse(flashcardRepository.save(flashcard));

        Flashcard saved = flashcardRepository.saveAndFlush(flashcard);
        return mapToResponse(saved);
    }

    // DELETE
    @Override
    public void delete(Long id) {
        flashcardRepository.deleteById(id);
    }

    // MAPPER
    private FlashcardResponse mapToResponse(Flashcard f) {

        return FlashcardResponse.builder()
                .id(f.getId())
                .title(f.getTitle())
                .visibility(f.getVisibility().name())
                .createdById(f.getCreatedBy().getId())
                .createdAt(f.getCreatedAt())
                .items(
                        f.getItems().stream()
                                .map(i -> FlashcardItemDto.builder()
                                        .id(i.getId())
                                        .frontText(i.getFrontText())
                                        .backText(i.getBackText())
                                        .imageUrl(i.getImageUrl())
                                        .build())
                                .toList()
                )
                .build();
    }
}