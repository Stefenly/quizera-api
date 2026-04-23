package co.istad.quizera.project.service.impl;

import co.istad.quizera.project.dto.classroom.ClassroomCreateRequest;
import co.istad.quizera.project.dto.classroom.ClassroomResponse;
import co.istad.quizera.project.entity.ClassStudent;
import co.istad.quizera.project.entity.Classroom;
import co.istad.quizera.project.entity.User;
import co.istad.quizera.project.mapper.ClassroomMapper;
import co.istad.quizera.project.repository.ClassStudentRepository;
import co.istad.quizera.project.repository.ClassroomRepository;
import co.istad.quizera.project.repository.UserRepository;
import co.istad.quizera.project.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final UserRepository userRepository;
    private final ClassStudentRepository classStudentRepository;
    private final ClassroomMapper classroomMapper;

    // CREATE
    @Override
    public ClassroomResponse createClassroom(Long teacherId, ClassroomCreateRequest request) {

        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Classroom classroom = Classroom.builder()
                .name(request.getName())
                .teacher(teacher)
                .classCode(generateClassCode())
                .build();

        classroomRepository.save(classroom);

        return classroomMapper.toDto(classroom);
    }

    // GET BY ID
    @Override
    public ClassroomResponse getClassroomById(Long id) {

        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classroom not found"));

        return classroomMapper.toDto(classroom);
    }

    // TEACHER CLASSES
    @Override
    public List<ClassroomResponse> getTeacherClasses(Long teacherId) {

        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        return classroomRepository.findByTeacher(teacher)
                .stream()
                .map(classroomMapper::toDto)
                .toList();
    }

    // STUDENT CLASSES
    @Override
    public List<ClassroomResponse> getStudentClasses(Long studentId) {

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return classStudentRepository.findByStudent(student)
                .stream()
                .map(cs -> classroomMapper.toDto(cs.getClassroom()))
                .toList();
    }

    // JOIN CLASSROOM
    @Override
    public ClassroomResponse joinClassroom(Long studentId, String classCode) {

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Classroom classroom = classroomRepository.findByClassCode(classCode)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        // prevent duplicate join
        if (classStudentRepository.findByClassroomAndStudent(classroom, student).isPresent()) {
            throw new RuntimeException("Already joined");
        }

        ClassStudent cs = ClassStudent.builder()
                .classroom(classroom)
                .student(student)
                .build();

        classStudentRepository.save(cs);

        return classroomMapper.toDto(classroom);
    }

    // DELETE
    @Override
    public void deleteClassroom(Long id) {

        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));

//        classroomRepository.delete(classroom);

        classroom.setDeleted(true);
        classroomRepository.save(classroom);
    }

    private String generateClassCode() {
        return "C" + (int)(Math.random() * 1_000_000);
    }
}