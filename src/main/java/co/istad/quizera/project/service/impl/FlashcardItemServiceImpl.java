package co.istad.quizera.project.service.impl;

import co.istad.quizera.project.dto.flashcard.FlashcardItemDto;
import co.istad.quizera.project.entity.Flashcard;
import co.istad.quizera.project.entity.FlashcardItem;
import co.istad.quizera.project.mapper.FlashcardMapper;
import co.istad.quizera.project.repository.FlashcardItemRepository;
import co.istad.quizera.project.repository.FlashcardRepository;
import co.istad.quizera.project.service.FlashcardItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlashcardItemServiceImpl implements FlashcardItemService {

    private final FlashcardItemRepository itemRepository;
    private final FlashcardRepository flashcardRepository;
    private final FlashcardMapper mapper;

    @Override
    public FlashcardItemDto create(Long flashcardId, FlashcardItemDto dto) {

        Flashcard flashcard = flashcardRepository.findById(flashcardId)
                .orElseThrow(() -> new RuntimeException("Flashcard not found"));

        FlashcardItem item = FlashcardItem.builder()
                .flashcard(flashcard)
                .frontText(dto.getFrontText())
                .backText(dto.getBackText())
                .imageUrl(dto.getImageUrl())
                .build();

        return mapper.toItemDto(itemRepository.save(item));
    }

    @Override
    public List<FlashcardItemDto> getByFlashcard(Long flashcardId) {

        Flashcard flashcard = flashcardRepository.findById(flashcardId)
                .orElseThrow();

        return itemRepository.findByFlashcard(flashcard)
                .stream()
                .map(mapper::toItemDto)
                .toList();
    }

    @Override
    public FlashcardItemDto update(Long id, FlashcardItemDto dto) {

        FlashcardItem item = itemRepository.findById(id)
                .orElseThrow();

        if (dto.getFrontText() != null)
            item.setFrontText(dto.getFrontText());

        if (dto.getBackText() != null)
            item.setBackText(dto.getBackText());

        if (dto.getImageUrl() != null)
            item.setImageUrl(dto.getImageUrl());

        return mapper.toItemDto(itemRepository.save(item));
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}
