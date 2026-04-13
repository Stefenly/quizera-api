package co.istad.quizera.project.repository;

import co.istad.quizera.project.entity.Classroom;
import co.istad.quizera.project.entity.Quiz;
import co.istad.quizera.project.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByClassroom(Classroom classroom);
    List<Quiz> findByCreatedBy(User createdBy);
    List<Quiz> findByIsPublicTrue();
    @EntityGraph(attributePaths = {"classroom", "createdBy"})
    @Query("SELECT q FROM Quiz q")
    List<Quiz> findAllForAdmin();
}