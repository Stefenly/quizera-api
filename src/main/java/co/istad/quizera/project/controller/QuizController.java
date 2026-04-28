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

    @GetMapping("/public")
    public ResponseEntity<List<QuizResponse>> getPublicQuizzes() {
        return ResponseEntity.ok(quizService.getAllPublicQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @PostMapping
    public ResponseEntity<QuizResponse> createQuiz(
            @RequestBody @Valid QuizCreateRequest request
    ) {
        return ResponseEntity.ok(quizService.createQuiz(request));
    }

    @GetMapping("/my")
    public ResponseEntity<List<QuizResponse>> getMyQuizzes() {
        return ResponseEntity.ok(quizService.getMyQuizzes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizResponse> updateQuiz(
            @PathVariable Long id,
            @RequestBody @Valid QuizCreateRequest request
    ) {
        return ResponseEntity.ok(quizService.updateQuiz(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.ok("Quiz deleted successfully");
    }


    @GetMapping
    public ResponseEntity<List<QuizResponse>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }
}