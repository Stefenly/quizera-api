package co.istad.quizera.project.repository;

import co.istad.quizera.project.entity.Flashcard;
import co.istad.quizera.project.entity.FlashcardItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FlashcardItemRepository extends JpaRepository<FlashcardItem, Long> {
    List<FlashcardItem> findByFlashcard(Flashcard flashcard);
}