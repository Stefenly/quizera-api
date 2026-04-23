package co.istad.quizera.project.service.impl;

import co.istad.quizera.project.dto.quiz.QuizCreateRequest;
import co.istad.quizera.project.dto.quiz.QuizResponse;
import co.istad.quizera.project.entity.Classroom;
import co.istad.quizera.project.entity.Quiz;
import co.istad.quizera.project.entity.User;
import co.istad.quizera.project.mapper.QuizMapper;
import co.istad.quizera.project.repository.ClassroomRepository;
import co.istad.quizera.project.repository.QuizRepository;
import co.istad.quizera.project.repository.UserRepository;
import co.istad.quizera.project.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final QuizMapper quizMapper;
    private final ClassroomRepository classroomRepository;

    // CREATE
    @Override
    public QuizResponse createQuiz(Long teacherId, QuizCreateRequest request) {

        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Classroom classroom = classroomRepository.findById(request.getClassroomId())
                .orElseThrow(() -> new RuntimeException("Classroom not found"));

        Quiz quiz = Quiz.builder()
                .title(request.getTitle())
                .createdBy(teacher)
                .classroom(classroom)
//                .isPublic(false)
                .isPublic(request.getIsPublic() != null ? request.getIsPublic() : false)
                .build();

        quizRepository.save(quiz);

        return quizMapper.toDto(quiz);
    }

    // GET BY ID
    @Override
    public QuizResponse getQuizById(Long id) {

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        return quizMapper.toDto(quiz);
    }

    // GET PUBLIC
    @Override
    public List<QuizResponse> getAllPublicQuizzes() {
        return quizRepository.findByIsPublicTrue()
                .stream()
                .map(quizMapper::toDto)
                .toList();
    }

    // GET ALL (ADMIN)
    @Override
    public List<QuizResponse> getAllQuizzes() {
        return quizRepository.findAll()
                .stream()
                .map(quizMapper::toDto)
                .toList();
    }

    // GET MY QUIZZES
    @Override
    public List<QuizResponse> getMyQuizzes(Long teacherId) {

        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return quizRepository.findByCreatedBy(teacher)
                .stream()
                .map(quizMapper::toDto)
                .toList();
    }

    // UPDATE
    @Override
    public QuizResponse updateQuiz(Long id, QuizCreateRequest request) {

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        quiz.setTitle(request.getTitle());

        quizRepository.save(quiz);

        return quizMapper.toDto(quiz);
    }

    // DELETE
    @Override
    public void deleteQuiz(Long id) {

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        quizRepository.delete(quiz);
    }
}