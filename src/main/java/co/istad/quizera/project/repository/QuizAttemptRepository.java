package co.istad.quizera.project.repository;

import co.istad.quizera.project.entity.Quiz;
import co.istad.quizera.project.entity.QuizAttempt;
import co.istad.quizera.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
    List<QuizAttempt> findByUser(User user);
    List<QuizAttempt> findByQuiz(Quiz quiz);
}