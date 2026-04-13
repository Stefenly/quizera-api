package co.istad.quizera.project.repository;

import co.istad.quizera.project.entity.Flashcard;
import co.istad.quizera.project.entity.User;
import co.istad.quizera.project.enums.Visibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    List<Flashcard> findByCreatedBy(User user);
    List<Flashcard> findByVisibility(Visibility visibility);
}