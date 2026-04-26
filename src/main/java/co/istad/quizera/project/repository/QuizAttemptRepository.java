package co.istad.quizera.project.repository;

import co.istad.quizera.project.entity.Quiz;
import co.istad.quizera.project.entity.QuizAttempt;
import co.istad.quizera.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//@Repository
//public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
//    List<QuizAttempt> findByUser(User user);
//    List<QuizAttempt> findByQuiz(Quiz quiz);
//}

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {

    List<QuizAttempt> findByUser(User user);

    List<QuizAttempt> findByQuiz(Quiz quiz);

    List<QuizAttempt> findByUserAndQuiz(User user, Quiz quiz);

    Optional<QuizAttempt> findTopByUserAndQuizOrderByScoreDesc(User user, Quiz quiz);

    int countByUserAndQuiz(User user, Quiz quiz);

    List<QuizAttempt> findByUserOrderByCompletedAtDesc(User user);
}