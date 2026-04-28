package co.istad.quizera.project.controller;

import co.istad.quizera.project.dto.quiz.QuizCreateRequest;
import co.istad.quizera.project.dto.quiz.QuizResponse;
import co.istad.quizera.project.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    // CREATE QUIZ
    @PostMapping("/{teacherId}")
    public ResponseEntity<QuizResponse> createQuiz(
            @Valid
            @PathVariable Long teacherId,
            @RequestBody QuizCreateRequest request
    ) {
        return ResponseEntity.ok(quizService.createQuiz(teacherId, request));
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<QuizResponse> getQuiz(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    // GET PUBLIC QUIZZES
    @GetMapping("/public")
    public ResponseEntity<List<QuizResponse>> getPublicQuizzes() {
        return ResponseEntity.ok(quizService.getAllPublicQuizzes());
    }

    // GET ALL (ADMIN)
    @GetMapping
    public ResponseEntity<List<QuizResponse>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    // GET MY QUIZZES
    @GetMapping("/my/{teacherId}")
    public ResponseEntity<List<QuizResponse>> getMyQuizzes(@PathVariable Long teacherId) {
        return ResponseEntity.ok(quizService.getMyQuizzes(teacherId));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<QuizResponse> updateQuiz(
            @Valid
            @PathVariable Long id,
            @RequestBody QuizCreateRequest request
    ) {
        return ResponseEntity.ok(quizService.updateQuiz(id, request));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.ok("Quiz deleted successfully");
    }
}