package co.istad.quizera.project.repository;

import co.istad.quizera.project.entity.ClassStudent;
import co.istad.quizera.project.entity.Classroom;
import co.istad.quizera.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ClassStudentRepository extends JpaRepository<ClassStudent, Long> {
    List<ClassStudent> findByClassroom(Classroom classroom);
    List<ClassStudent> findByStudent(User student);
    Optional<ClassStudent> findByClassroomAndStudent(Classroom classroom, User student);
}