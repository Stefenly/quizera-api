package co.istad.quizera.project.repository;

import co.istad.quizera.project.entity.Classroom;
import co.istad.quizera.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Optional<Classroom> findByClassCode(String classCode);
    List<Classroom> findByTeacher(User teacher);
}